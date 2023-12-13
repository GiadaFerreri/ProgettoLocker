package it.polito.progettolocker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.Graphic.HeaderDouble

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Delivery(mainActivity: MainActivity, navController: NavController){
    DaEffettuare(mainActivity, navController)
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DaEffettuare(mainActivity: MainActivity,navController: NavController){
    HeaderDouble("DA EFFETTUARE", weight1 = FontWeight.Bold, "IN CORSO", weight2 = FontWeight.Normal,
        onClick= {navController.navigate("InCorso")})
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    HeaderDouble("DA EFFETTUARE", weight1 = FontWeight.Normal, "IN CORSO", weight2 = FontWeight.Bold,
        onClick= {navController.navigate("DaEffettuare")})
}