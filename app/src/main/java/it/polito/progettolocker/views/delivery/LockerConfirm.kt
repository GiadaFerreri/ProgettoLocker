package it.polito.progettolocker.views.delivery

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Seconda pagina del Locker
fun LockerConfirm(mainActivity: MainActivity, navController: NavController){

    /*
    val db = mainActivity.viewModel.db
    val currentShipping = mainActivity.viewModel.selectedShipping.value
    val ref = db.child("Shipping/${currentShipping.shippingId}")


    val listener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Recupera il nuovo valore dello stato della spedizione
            val shipping = dataSnapshot.getValue(Shipping::class.java)
            val state = shipping!!.state

            // Invia una notifica al cliente
            val notification = NotificationCompat.Builder(mainActivity, mainActivity.notificationChannelId)
                .setContentTitle("Aggiornamento spedizione 2")
                .setContentText("La spedizione ${shipping.shippingId} è ora $state")
                .setSmallIcon(R.drawable.logo_round)
                .build()
            mainActivity.notificationManager.notify(shipping.shippingId!!.toInt(), notification)
            MyFirebaseMessagingService.sendNotification(mainActivity,shipping)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Gestisci l'errore
        }
    }
    ref.addValueEventListener(listener)


     */

    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }
    val ctx = LocalContext.current
    Scaffold(
        containerColor = Color.White,
        topBar = {
            HeaderX(text = "LOCKER", navController = navController, onClickDestination = "InCorso")
        },

        bottomBar = {
            Row(modifier = Modifier.padding(bottom = 50.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                CardWarning(
                    text = "Assicurati che il cassetto sia chiuso correttamente prima di confermare.",
                    mainActivity = mainActivity,
                    navController = navController
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                if (firstTry) CardsJustText(text1 = "IL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?")
                else CardsJustText(text1 = "RIAPERTURA DEL CASSETTO AVVENUTA CON SUCCESSO.\n" + "\nIL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Buttons(text = "CONFERMA",
                    onClickHandler = {
                        val shipping = mainActivity.viewModel.selectedShipping.value
                        //Imposta stato a DELIVERED
                        mainActivity.viewModel.db.child("Shipping/${shipping.shippingId}/state")
                            .setValue(States.DELIVERED)
                        var desc =
                            if (shipping.articles!!.size - 1 == 0) "${shipping.articles!![0].name.toString()}"
                            else if (shipping.articles.size - 1 == 1) "${shipping.articles!![0].name.toString()} + ${(shipping.articles.size - 1)} altro articolo"
                            else "${shipping.articles!![0].name.toString()} + ${(shipping.articles.size - 1)} altri articoli"
                        //mainActivity.viewModel.db.child("Shipping/${shipping.shippingId}/state").setValue(States.DELIVERED)
                        //Invia la notifica al cliente
                        mainActivity.createNotification(
                            "Spedizione consegnata",
                            "Il tuo ordine contenente $desc è pronto per il ritiro al locker ${shipping.lockerId!!.uppercase()}",
                            shipping.userId!!
                        )
                        //Chiude il cassetto
                        mainActivity.viewModel.db.child("Locker/${shipping.lockerId}/compartments/${shipping.compartmentId}/chiuso")
                            .setValue(true)
                        navController.navigate("DaEffettuare")
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(
                    "Problemi durante il deposito?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


                if (firstTry) { //se è la prima volta esce questo bottone
                    //TODO: questo bottone sotto deve cambiare testo se è la seconda volta che si carica la pagina
                    Buttons(
                        text = "RIAPRI IL CASSETTO",
                        onClickHandler = {
                            //navController.navigate("Locker")
                            val shipping = mainActivity.viewModel.selectedShipping.value
                            mainActivity.viewModel.db.child("Locker/${shipping.lockerId}/compartments/${shipping.compartmentId}/chiuso")
                                .setValue(false)
                            setFirstTryDone(!firstTry)
                        }
                    )
                }
                if (!firstTry) {
                    Buttons(
                        text = "CONTATTA L'ASSISTENZA",
                        onClickHandler = {
                            val u = Uri.parse("tel:" + "3462497262")

                            // Create the intent and set the data for the
                            // intent as the phone number.
                            val i = Intent(Intent.ACTION_DIAL, u)
                            try {

                                // Launch the Phone app's dialer with a phone
                                // number to dial a call.
                                ctx.startActivity(i)
                            } catch (s: SecurityException) {

                                // show() method display the toast with
                                // exception message.
                                Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                                    .show()
                            }
                            //navController.navigate("DaEffettuare")
                        },

                        )
                }
            }


        }
    }


    BackHandler (enabled = true){
        navController.navigate("InCorso")
    }
}