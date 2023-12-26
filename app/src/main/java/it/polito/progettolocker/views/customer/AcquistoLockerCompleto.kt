package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@Composable
fun AcquistoLockerCompleto(mainActivity: MainActivity, navController: NavController){
    Column {
        Row {
            HeaderX(text = "ACQUISTO", navController = navController)
        }
        Row (modifier = Modifier.padding(16.dp)){
           CardWarning(
               text = "Tutti i locker sono occupati al momento. La spedizione potrebbe richiedere più tempo del previsto.",
               mainActivity = mainActivity ,
               navController = navController )
        }
        Row (modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth() , 
            horizontalArrangement = Arrangement.Center)
        {
            Text(
                text = "PROCEDERE COMUNQUE ALL'ACQUISTO?",
                )
        }
        Row (modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth() ,
            horizontalArrangement = Arrangement.Center)
        {
            Buttons("ACQUISTA",
                onClickHandler = { navController.navigate("Acquisto",) })

           }
        }

    BackHandler (enabled = true){
        navController.navigate("Carrello")
    }
}