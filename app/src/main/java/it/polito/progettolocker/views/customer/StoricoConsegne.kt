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
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.HeaderDouble

@Composable

fun StoricoConsegne(mainActivity: MainActivity, navController: NavController){
    Row{
        CardOrder(
            orderNumber = "76",
            description = "RITIRATO IL 18 OTTOBRE",
            leftButton = false,
            rightButton = false,
            mainActivity = mainActivity,
            navController = navController
        )
    }

}