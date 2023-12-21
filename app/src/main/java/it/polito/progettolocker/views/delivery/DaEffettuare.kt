package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder

@Composable
fun DaEffettuare(mainActivity: MainActivity, navController: NavController){
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

        Row{
            CardOrder(
                orderNumber = "242T",
                description = "CONSEGNA AL LOCKER DA EFFETTUARE ENTRO LE 18:00",
                leftButtonText = "PRESA IN CARICO",
                mainActivity = mainActivity,
                navController = navController,
                onClickDestination = "InCorso"
            )
        }
        Row{
            CardOrder(
                orderNumber = "4RIU",
                description = "CONSEGNA AL LOCKER DA EFFETTUARE ENTRO LE 18:00",
                leftButtonText = "PRESA IN CARICO",
                mainActivity = mainActivity,
                navController = navController,
                onClickDestination = "InCorso"
            )
        }
    }


    //con freccia indietro si esce dall'applicazione
    BackHandler (enabled = true){
        mainActivity.finish()    }
}