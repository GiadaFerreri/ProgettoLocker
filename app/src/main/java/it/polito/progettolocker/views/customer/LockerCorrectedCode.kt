package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX
@Composable
fun LockerCorrectedCode(mainActivity: MainActivity, navController: NavController){
    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }

    Column(  modifier = Modifier
        .fillMaxWidth()){
        Row {
            HeaderX(text = "LOCKER", navController = navController, onClickDestination = "LockerCode")

        }
        Row ( modifier = Modifier.fillMaxWidth().padding(top=150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            CardsJustText(text1 = "CODICE INSERITO CORRETTO.\n" +
                    "RITIRARE IL PACCO.")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            if(firstTry) { //se è la prima volta esce questo bottone
                //TODO: questo bottone sotto deve cambiare testo se è la seconda volta che si carica la pagina
                Buttons(
                    text = "RIAPRI IL CASSETTO",
                    onClickHandler = {
                        //navController.navigate("Locker")
                        setFirstTryDone(!firstTry)
                    }
                )
            }
            if(!firstTry){
                Buttons(
                    text = "CONTATTA L'ASSISTENZA",
                    onClickHandler = { navController.navigate("Customer") },

                    )
            }
        }

    }

    BackHandler (enabled = true){
        navController.navigate("LockerCode")
    }
}