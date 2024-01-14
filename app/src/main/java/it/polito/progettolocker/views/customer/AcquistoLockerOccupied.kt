package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@Composable
//SCHERMATA LOCKER OCCUPATI
fun AcquistoLockerOccupied(mainActivity: MainActivity, navController: NavController){
    Column (){
        Row {
            HeaderX(text = "ACQUISTO", navController =navController, onClickDestination = "Customer")
        }
        Row( modifier = Modifier.fillMaxWidth().padding(top=80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            CardWarning(
                text = "Tutti i locker sono occupati al momento. La spedizione potrebbe richiedere più tempo del previsto.",
                mainActivity = mainActivity,
                navController = navController
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top=20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Text(text="PROCEDERE COMUNQUE ALL'ACQUISTO?", modifier = Modifier
                .padding(30.dp,30.dp,30.dp,0.dp))
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top=50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Buttons(text = "ACQUISTA", onClickHandler = {navController.navigate("Acquisto")})
        }

    }


    BackHandler (enabled = true){
        navController.navigate("Carrello")
    }
}