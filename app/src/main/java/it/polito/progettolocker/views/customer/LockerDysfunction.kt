package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity

@Composable

fun LockerDysfunction(mainActivity: MainActivity, navController: NavController){


    BackHandler (enabled = true){
        navController.navigate("SpedizioniInCorso")
    }
}