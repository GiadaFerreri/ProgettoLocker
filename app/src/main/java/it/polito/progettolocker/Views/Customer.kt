package it.polito.progettolocker.Views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.Graphic.FooterHome
import it.polito.progettolocker.MainActivity

@Composable
fun Customer(mainActivity: MainActivity, navController: NavController){
    FooterHome(navController = navController)
}