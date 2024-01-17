package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.HeaderX

@Composable

fun DettagliOrdine(mainActivity: MainActivity, navController: NavController){
Row{
    HeaderX(text = "DETTAGLI ORDINE", navController = navController, onClickDestination = "Spedizioni")
}

    Column( verticalArrangement = Arrangement.spacedBy(16.dp),horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top=200.dp)) {

        //TODO: mettere immagini articoli qua
        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "ZARA LCKR",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp,
                color = Color(0xFF000000)
            )
            Text(text = "LOCKER LINGOTTO", fontSize = 12.sp, color = Color(0xFF000000), modifier = Modifier.padding(top=5.dp),)
            Text(text = "VIA NIZZA 294, 10126 TORINO", modifier = Modifier.padding(top=1.dp), fontSize = 12.sp, color = Color(0xFF000000))
        }
    }
    BackHandler (enabled = true){
        navController.navigate("SpedizioniInCorso")
    }

}