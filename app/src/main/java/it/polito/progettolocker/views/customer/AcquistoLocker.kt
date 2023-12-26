package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.HeaderX

@Composable

//Prima pagina acquisto
fun AcquistoLocker(mainActivity: MainActivity, navController: NavController) {
    Column (){
        Row (){
           CardOrder(orderNumber = "LOCKER LINGOTTO",
               description = "VIA NIZZA 294, 10126 TORINO\nAPERO 24H SU 24",
               mainActivity = mainActivity,
               navController = navController,
               onClickDestination = "Acquisto")
        }

    }


    BackHandler (enabled = true){
        navController.navigate("Carrello")
    }

}