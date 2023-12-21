package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.FooterTotal
import it.polito.progettolocker.graphic.FooterWarning
import it.polito.progettolocker.graphic.HeaderX

@Composable
fun Customer(mainActivity: MainActivity, navController: NavController){
    FooterHome(navController = navController)
    //Catalogo(mainActivity = mainActivity, navController = navController)
    //Carrello(mainActivity = mainActivity, navController = navController)
    //FooterTotal(price="10.00",navController = navController)
    //FooterDoubleBlack(price = 30,navController = navController)
    //FooterWarning(text = "IL TUO ACQUISTO È ANDATO A BUON FINE!",navController = navController)

    //con freccia indietro si esce dall'applicazione
    BackHandler (enabled = true){
        mainActivity.finish()
    }
}