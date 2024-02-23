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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DettagliOrdine(mainActivity: MainActivity, navController: NavController){

    val tempList = mutableListOf<Shipping>()
    var sliderPosition by remember { mutableFloatStateOf(0f) }

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

                    LazyRow (modifier=Modifier.padding(start=20.dp, top=10.dp, end=20.dp)){
                        items(result.data as List<Shipping>) { shipping ->

                                if(shipping.shippingId == mainActivity.shippingId){
                                    for(article in shipping.articles!!) {
                                        Column(verticalArrangement = Arrangement.Top) {
                                            Column(modifier = Modifier.padding(top = 5.dp)) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(2.dp)
                                                ) {
                                                    AsyncImage(
                                                        model = article.image,
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
                LazyRow(modifier=Modifier.padding(start=20.dp, top=10.dp)){
                    items(result.data as List<Shipping>) { shipping ->
                        if (shipping.shippingId == mainActivity.shippingId) {
                            Text(
                                text = "STATO DELL'ORDINE: ",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = Color(0xFF000000),
                            )
                            if (shipping.state == States.HANDLED) {
                                Text(
                                    text = "IN CONSEGNA",
                                    fontSize = 15.sp,
                                    color = Color(0xFF000000),
                                )
                                sliderPosition=2f

                            }
                            if (shipping.state == States.PENDING) {
                                Text(
                                    text = "INOLTRATO",
                                    fontSize = 15.sp,
                                    color = Color(0xFF000000),
                                )
                                sliderPosition=1f
                            }
                            if (shipping.state == States.DELIVERED) {
                                Text(
                                    text = "CONSEGNATO",
                                    fontSize = 15.sp,
                                    color = Color(0xFF000000),
                                )
                               sliderPosition=3f
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
                .padding(start = 15.dp, end= 20.dp)
                .fillMaxWidth()
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 2,
                valueRange = 0f..3f,
                enabled = false,

            )
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