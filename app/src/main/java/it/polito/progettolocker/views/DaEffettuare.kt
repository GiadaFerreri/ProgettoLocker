package it.polito.progettolocker.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.MainActivity

@Composable
fun DaEffettuare(mainActivity: MainActivity, navController: NavController){
    HeaderDouble(
        text1 = "DA EFFETTUARE",
        weight1 = FontWeight.Bold,
        text2 = "IN CORSO",
        weight2 = FontWeight.Normal,
        onClickListener2 = "InCorso",
        navController = navController
    )
}