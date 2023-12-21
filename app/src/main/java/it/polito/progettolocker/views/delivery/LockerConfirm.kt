package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX

@Composable
//Seconda pagina del Locker
fun LockerConfirm(mainActivity: MainActivity, navController: NavController){

    Column(  modifier = Modifier
        .fillMaxWidth()){
        Row {
            HeaderX(text = "LOCKER", navController = navController, onClickDestination = "InCorso")

        }
        Row ( modifier = Modifier.fillMaxWidth().padding(top=150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            CardsJustText(text1 = "IL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Buttons(text = "CONFERMA", onClickHandler = {navController.navigate("DaEffettuare")})
            Buttons(text = "RIAPRI IL CASSETTO",onClickHandler = {navController.navigate("Locker")})
        }

    }

    BackHandler (enabled = true){
        navController.navigate("Locker")
    }
}