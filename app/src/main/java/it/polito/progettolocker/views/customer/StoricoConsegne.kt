package it.polito.progettolocker.views.customer

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.CardProduct
import it.polito.progettolocker.graphic.CardWarning

@Composable

fun StoricoConsegne(mainActivity: MainActivity, navController: NavController){


    val tempList = mutableListOf<Shipping>()
    var spedizioni=false

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

    Column {

        when (val result = mainActivity.viewModel.shippingState.value) {
            is DataState.Loading -> {
                Box() {
                    CircularProgressIndicator()
                }
            }

            is DataState.Success -> {
                var quantityTot = 0
                var shippings= result.data as MutableList<Shipping>

                for(shipping in shippings){
                    if(shipping.state== States.CONCLUDED) {
                        for (article in shipping.articles!!) {
                            quantityTot += article.quantity!!.toInt()
                        }
                    }
                }

                Row {
                    LazyColumn(modifier= Modifier.padding(bottom=30.dp)) {
                        items(result.data as List<Shipping>) { shipping ->

                            Row{
                                if(shipping.state == States.CONCLUDED){
                                    for (article in shipping.articles!!){
                                        var desc = if(shipping.articles.size - 1 == 0) "${article.name.toString()}"
                                        else if(shipping.articles.size - 1 == 1) "${article.name.toString()} + ${(shipping.articles.size - 1)} articolo"
                                        else "${article.name.toString()} + ${(shipping.articles.size - 1)} articoli"
                                        CardProduct(dataRitiro = "ORDINE RITIRATO", descrizioneProdotto = desc, image=article.image.toString())
                                        spedizioni = true
                                    }
                                }
                            }
                        }

                    }
                    if (!spedizioni) {

                            Row(
                                modifier = Modifier.padding(start = 70.dp, top = 350.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "NESSUNA SPEDIZIONE PASSATA",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = Color(0xFF000000)
                                )


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
    }


}