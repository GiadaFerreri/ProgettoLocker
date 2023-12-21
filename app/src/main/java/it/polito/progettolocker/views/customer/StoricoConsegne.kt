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
import it.polito.progettolocker.graphic.CardProduct
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.HeaderDouble

@Composable

fun StoricoConsegne(mainActivity: MainActivity, navController: NavController){
    Column {
        Row(){

        }
    }
    Row{
        CardProduct(dataRitiro = "RITIRATO IL 22/12", descrizioneProdotto = "MAGLIONCINO BIANCO DI LANA\n +altri 5" )
    }

}