package it.polito.progettolocker.views.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@Composable
fun LockerBlock(mainActivity: MainActivity, navController: NavController){
    Column {
        Row(){
            HeaderX(text ="LOCKER" , navController = navController, onClickDestination = "Customer")
        }
        Row {
            CardWarning(
                text = "Tentativi di inserimento del codice terminati. Riprovare più tardi",
                mainActivity = mainActivity,
                navController = navController
            )

        }
    }
}