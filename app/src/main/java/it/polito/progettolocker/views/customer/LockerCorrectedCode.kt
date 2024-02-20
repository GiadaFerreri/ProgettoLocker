package it.polito.progettolocker.views.customer

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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX
@Composable
fun LockerCorrectedCode(mainActivity: MainActivity, navController: NavController){
    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }

    val selectedShipping = mainActivity.viewModel.selectedShipping.value

    val ctx = LocalContext.current

    Column(  modifier = Modifier
        .fillMaxWidth()){
        Row {
            HeaderX(text = "LOCKER", navController = navController, onClickDestination = "Customer")

        }

        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            if(firstTry) CardsJustText(text1 =  "CODICE INSERITO CORRETTO.\n" + "RITIRARE IL PACCO.")
            else CardsJustText(text1 = "CASSETTO APERTO NUOVAMENTE.\n" + "RITIRARE IL PACCO.")
        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Buttons(
                text = "TORNA ALLA HOME",
                onClickHandler = {
                    //Imposta la spedizione a CONCLUDED
                    mainActivity.viewModel.db.child("Shipping/${selectedShipping.shippingId}/state").setValue(States.CONCLUDED)
                    //Chiude il vano
                    mainActivity.viewModel.db.child("Locker/${selectedShipping.lockerId}/compartments/${selectedShipping.compartmentId}/chiuso").setValue(true)
                    //Libera il vano
                    mainActivity.viewModel.db.child("Locker/${selectedShipping.lockerId}/compartments/${selectedShipping.compartmentId}/inuso").setValue(false)
                    navController.navigate("Customer")
                }
            )
        }
        Row ( modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            Text("Problemi durante il ritiro?",fontSize = 15.sp,)
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
                        //Apre il vano
                        mainActivity.viewModel.db.child("Locker/${selectedShipping.lockerId}/compartments/${selectedShipping.compartmentId}/chiuso").setValue(false)

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
                        navController.navigate("Customer")
                    },

                    )
            }
        }

    }

    DisposableEffect(Unit){
        onDispose {
            //Imposta la spedizione a CONCLUDED
            mainActivity.viewModel.db.child("Shipping/${selectedShipping.shippingId}/state").setValue(States.CONCLUDED)
            //Chiude il vano
            mainActivity.viewModel.db.child("Locker/${selectedShipping.lockerId}/compartments/${selectedShipping.compartmentId}/chiuso").setValue(true)
            //Libera il vano
            mainActivity.viewModel.db.child("Locker/${selectedShipping.lockerId}/compartments/${selectedShipping.compartmentId}/inuso").setValue(false)
        }
    }

    BackHandler (enabled = true){
        navController.navigate("LockerCode")
    }
}