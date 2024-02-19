package it.polito.progettolocker.views.customer

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@Composable

fun DettagliOrdine(mainActivity: MainActivity, navController: NavController){

    val tempList = mutableListOf<Shipping>()

    mainActivity.viewModel.db.child("Shipping")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for(snapshot in snapshot.children){
                    val shipping = snapshot.getValue(Shipping::class.java)
                    tempList.add(shipping!!)
                }
                mainActivity.viewModel.shippingState.value = DataState.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

Row{
    HeaderX(text = "DETTAGLI ORDINE", navController = navController, onClickDestination = "Spedizioni")
}

    Column( verticalArrangement = Arrangement.spacedBy(16.dp),horizontalAlignment = Alignment.Start, modifier = Modifier.padding(top=70.dp)) {

        when (val result = mainActivity.viewModel.shippingState.value) {
            is DataState.Loading -> {
                Box() {
                    CircularProgressIndicator()
                }
            }

            is DataState.Success -> {
                Row {
                    LazyRow {
                        items(result.data as List<Shipping>) { shipping ->
                            Row{
                                if(shipping.shippingId==mainActivity.shippingId){
                                    for(article in shipping.articles!!){
                                        AsyncImage(
                                            model =article.image,
                                            contentDescription = "Immagine prodotto",
                                            modifier = Modifier
                                                .border(0.5.dp, Color.Black)
                                        )
                                    }

                                }



                            }
                        }
                    }
                }
            }

            is DataState.Failure -> {
                CardWarning(
                    text = result.message,
                    mainActivity = mainActivity,
                    navController = navController
                )
            }

            else -> {
                CardWarning(
                    text = "Error fetching data",
                    mainActivity = mainActivity,
                    navController = navController
                )
            }

        }
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ZARA LCKR",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = Color(0xFF000000)
            )
            Text(text = "LOCKER LINGOTTO", fontSize = 12.sp, color = Color(0xFF000000), modifier = Modifier.padding(top=5.dp),)
            Text(text = "VIA NIZZA 294, 10126 TORINO", modifier = Modifier.padding(top=1.dp), fontSize = 12.sp, color = Color(0xFF000000))
        }
    }
    BackHandler (enabled = true){
        navController.navigate("Spedizioni")
    }

}