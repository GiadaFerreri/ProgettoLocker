package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.FooterHome
@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController){
    CardOrderPhoto(navController = navController, textProduct = "ZW COLLECTION SEQUINNED VELVET BLAZER", price = 99.95F)
    FooterHome(navController = navController)

    BackHandler (enabled = true){
        navController.navigate("Customer")
    }

}