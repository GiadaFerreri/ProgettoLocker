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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.MyFirebaseMessagingService
import it.polito.progettolocker.R
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX

@Composable
//Seconda pagina del Locker
fun LockerConfirm(mainActivity: MainActivity, navController: NavController){

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
                .setContentTitle("Aggiornamento spedizione")
                .setContentText("La spedizione ${shipping.shippingId} è ora $state")
                .setSmallIcon(R.drawable.logo_round)
                .build()
            //mainActivity.notificationManager.notify(shipping.shippingId!!.toInt(), notification)
            MyFirebaseMessagingService.sendNotification(mainActivity,shipping)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Gestisci l'errore
        }
    }
    ref.addValueEventListener(listener)


    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }
    val ctx = LocalContext.current

    Column(  modifier = Modifier
        .fillMaxWidth()){
        Row {
            HeaderX(text = "LOCKER", navController = navController, onClickDestination = "InCorso")

        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            if(firstTry) CardsJustText(text1 = "IL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?\nASSICURATI CHE IL CASSETTO SIA CHIUSO CORRETTAMENTE")
           else CardsJustText(text1 = "RIAPERTURA DEL CASSETTO AVVENUTA CON SUCCESSO.\n" + "\nIL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?")
        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Buttons(text = "CONFERMA",
                onClickHandler = {
                    val shipping = mainActivity.viewModel.selectedShipping.value
                    //Imposta stato a DELIVERED
                    mainActivity.viewModel.db.child("Shipping/${shipping.shippingId}/state").setValue(States.DELIVERED)
                    //Chiude il cassetto
                    mainActivity.viewModel.db.child("Locker/${shipping.lockerId}/compartments/${shipping.compartmentId}/chiuso").setValue(true)
                    navController.navigate("DaEffettuare")
                }
            )
        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Text("Problemi durante il deposito?",fontSize = 15.sp,)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            if(firstTry) { //se è la prima volta esce questo bottone
                //TODO: questo bottone sotto deve cambiare testo se è la seconda volta che si carica la pagina
                Buttons(
                    text = "RIAPRI IL CASSETTO",
                    onClickHandler = {
                        //navController.navigate("Locker")
                        val shipping = mainActivity.viewModel.selectedShipping.value
                        mainActivity.viewModel.db.child("Locker/${shipping.lockerId}/compartments/${shipping.compartmentId}/chiuso").setValue(false)
                        setFirstTryDone(!firstTry)
                    }
                )
            }
            if(!firstTry){
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
                        navController.navigate("DaEffettuare")
                    },

                    )
            }
        }

    }

    BackHandler (enabled = true){
        navController.navigate("Locker")
    }
}