package it.polito.progettolocker.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.FooterTotal

@Composable
fun Customer(mainActivity: MainActivity, navController: NavController){
   FooterHome(navController = navController)
    //FooterTotal(price="10.00")
}