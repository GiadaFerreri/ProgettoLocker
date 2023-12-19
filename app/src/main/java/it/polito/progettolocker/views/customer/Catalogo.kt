package it.polito.progettolocker.views.customer

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.FooterHome
@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController){
    CardOrder(navController = navController, textProduct = "ZW COLLECTION SEQUINNED VELVET BLAZER", price = 99.95F)

}