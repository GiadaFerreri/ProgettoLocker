package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.HeaderX

@Composable
fun Carrello(mainActivity: MainActivity, navController: NavController){
    Column {
        Row (){
            HeaderX(text = "CARRELLO", navController = navController )
        }
        Row {
            CardProductCard(navController = navController, textProduct = "VESTITO LINGERIE ZW COLLECTION", price = 88.95F)
        }
    }

    BackHandler (enabled = true){
        navController.navigate("Customer")
    }


}