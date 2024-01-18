package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.CardPurchase
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.HeaderX

@Composable

//Prima pagina acquisto
fun AcquistoLocker(mainActivity: MainActivity, navController: NavController) {
    Column (){
        Row {
            HeaderX(text = "ACQUISTO", navController =navController, onClickDestination = "Carrello")
        }
        Row(modifier=Modifier.padding(start=30.dp,top=30.dp,end=30.dp, bottom=5.dp)){
            Text("SELEZIONA IL LOCKER IN CUI RITIRARE IL TUO ORDINE:", fontSize = 15.sp, fontWeight = FontWeight.Medium )
        }
            LazyColumn {
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
            }
        }


    BackHandler (enabled = true){
        navController.navigate("Carrello")
    }

}