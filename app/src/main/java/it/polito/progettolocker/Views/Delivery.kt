package it.polito.progettolocker.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.Graphic.HeaderDouble
import it.polito.progettolocker.MainActivity

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Delivery(mainActivity: MainActivity, navController: NavController){
    DaEffettuare(mainActivity, navController)
}

@RequiresApi(Build.VERSION_CODES.Q)
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

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    HeaderDouble(
        text1 = "DA EFFETTUARE",
        weight1 = FontWeight.Normal,
        text2 = "IN CORSO",
        weight2 = FontWeight.Bold,
        onClickListener1 = "DaEffettuare",
        navController = navController
    )
}