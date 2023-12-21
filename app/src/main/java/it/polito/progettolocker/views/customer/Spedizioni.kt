package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.HeaderDouble

@Composable
fun Spedizioni(mainActivity: MainActivity, navController: NavController) {
    var buttonLeft = true
    Column{
        Row{
            HeaderDouble(
                text1 = "IN CORSO",
                weight1 = if(buttonLeft) FontWeight.Bold else FontWeight.Normal,
                text2 = "STORICO CONSEGNE",
                weight2 = if(buttonLeft) FontWeight.Normal else FontWeight.Bold,
                onClickHandler1 = { if(!buttonLeft) { buttonLeft = true; } },
                onClickHandler2 = { if(buttonLeft) buttonLeft = false },
                navController = navController)
        }
        Row{
            Text(text = buttonLeft.toString())
        }
        Row(modifier = Modifier.height(IntrinsicSize.Min)){
            /* TODO: Scrollable */
            if(buttonLeft)
                SpedizioniInCorso(mainActivity = mainActivity, navController = navController)
            else
                StoricoConsegne(mainActivity = mainActivity, navController = navController)
        }
        Row(
            //verticalAlignment = Alignment.Bottom
        ){
            FooterHome(navController = navController)
        }
    }








    BackHandler (enabled = true){
        navController.navigate("Customer")
    }

}