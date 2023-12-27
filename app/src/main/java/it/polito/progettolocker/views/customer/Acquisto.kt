package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardPhotoSwipe
import it.polito.progettolocker.graphic.FooterTotal
import it.polito.progettolocker.graphic.HeaderX

@Composable
//seconda pagina acquisto
fun Acquisto(mainActivity: MainActivity, navController: NavController){
    var openButton by remember { mutableStateOf(true) }

    Column (){
        Row(){
            HeaderX(text = "ACQUISTO", navController = navController, onClickDestination = "Carrello")
        }
        Row (modifier = Modifier.padding(16.dp)){
            Text(text = "3 ARTICOLI")
        }
        Row {
            CardPhotoSwipe(navController = navController)
        }
        Row (){
            Text(
                text = "ZARA LCKR",
                modifier = Modifier.padding(16.dp,5.dp),
                fontWeight = FontWeight.Medium
            )
        }
        Row (Modifier.fillMaxWidth()){
            Column (Modifier.weight(2f)){
                Text(
                    text = "LOCKER LINGOTTO\nVIA NIZZA 294, 10126 TORINO",
                    modifier = Modifier.padding(16.dp, 5.dp))
            }
            if(openButton){
                Column(Modifier.weight(1f)
                ) {
                    Buttons("MODIFICA",
                        onClickHandler = { navController.navigate("AcquistoLocker",) })
                }
            }

        }
    }
    FooterTotal(price = 89, navController = navController)

    BackHandler (enabled = true){
        navController.navigate("AcquistoLocker")
    }

}