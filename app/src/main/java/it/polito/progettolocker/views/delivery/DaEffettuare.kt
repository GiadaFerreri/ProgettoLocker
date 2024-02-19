package it.polito.progettolocker.views.delivery

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderDouble

@Composable
fun DaEffettuare(mainActivity: MainActivity, navController: NavController){

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

    Column{
        Row{
            HeaderDouble(
                text1 = "DA EFFETTUARE",
                weight1 = FontWeight.Bold,
                text2 = "IN CORSO",
                weight2 = FontWeight.Normal,
                onClickHandler1 = {},
                onClickHandler2 = { navController.navigate("InCorso") },
                navController = navController
            )
        }

        Row {
            when (val result = mainActivity.viewModel.shippingState.value) {
                is DataState.Loading -> {
                    Box() {
                        CircularProgressIndicator()
                    }
                }

                is DataState.Success -> {
                    Row {
                        LazyColumn {
                            items(result.data as List<Shipping>) { shipping ->
                                Row {
                                    if (shipping.state == States.PENDING) {
                                        CardOrder(
                                            shipping = shipping,
                                            orderNumber = shipping.shippingId!!.toString(),
                                            description = "CONSEGNA AL LOCKER LINGOTTO",
                                            leftButtonText = "PRESA IN CARICO",
                                            mainActivity = mainActivity,
                                            navController = navController,
                                            onClickDestination = "InCorso",
                                            toHandle = true
                                        )
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

            //TODO: creare le card dalla lista di spedizioni da efffettuare
            /*TODO: quando si clicca sul bottone 'presa in carico' si deve eliminare la card da questa schermata e aggiungerla alla schermata 'In corso'
                    ovvero si deve cambiare lo stato dell'ordine in questione*/
        }
    }


    //con freccia indietro si esce dall'applicazione
    BackHandler (enabled = true){
        mainActivity.finish()    }
}