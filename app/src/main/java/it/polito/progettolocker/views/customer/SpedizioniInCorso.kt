package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.graphic.HeaderX

@Composable

fun SpedizioniInCorso(mainActivity: MainActivity, navController: NavController) {
    Column{
        Row{
            CardWarning(
                text = "Tutti i locker sono occupati al momento. La spedizione potrebbe richiedere più tempo del previsto.",
                mainActivity = mainActivity,
                navController = navController
            )
        }
        Row{
            CardOrder(
                orderNumber = "242",
                description = "IL TUO PACCO E' IN CONSEGNA",
                mainActivity = mainActivity,
                navController = navController
            )
        }
        Row{
            CardOrder(
                orderNumber = "186",
                description = "IL TUO PACCO E' STATO CONSEGNATO",
                leftButton = true,
                rightButton = true,
                mainActivity = mainActivity,
                navController = navController
            )
        }
    }

}