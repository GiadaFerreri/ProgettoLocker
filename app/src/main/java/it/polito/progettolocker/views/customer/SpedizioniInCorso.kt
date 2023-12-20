package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.HeaderX

@Composable

fun SpedizioniInCorso(mainActivity: MainActivity, navController: NavController) {

    Column {
        Row (){
            HeaderX(text = "SPEDIZIONI", navController = navController, onClickDestination = "Customer")
        }
    }
    BackHandler (enabled = true){
        navController.navigate("Customer")
    }

}