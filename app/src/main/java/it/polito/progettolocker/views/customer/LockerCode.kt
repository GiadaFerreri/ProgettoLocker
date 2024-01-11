package it.polito.progettolocker.views.customer

import android.graphics.Paint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun LockerCode(mainActivity: MainActivity, navController: NavController){
    Column {
        Row(){
            HeaderX(text ="LOCKER" , navController = navController, onClickDestination = "SpedizioniInCorso")
        }
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
            .padding(top = 50.dp, bottom = 25.dp)
            .fillMaxWidth())
        {

            Text(
                text = "INSERISCI IL CODICE CHE VEDI SULLO SCHERMO DEL LOCKER",
                fontSize = 14.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
    }
        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            var value by remember { mutableStateOf("") }

            TextField(
                value = value,
                onValueChange = { value = it },
                label = { Text("CODICE LOCKER") },
                maxLines = 1,
                textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            )
        }
        Row( horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp))
        {
            Buttons(text = "CONFERMA", onClickHandler = {navController.navigate("LockerCorrectedCode")})
        }

    }



    BackHandler (enabled = true){
        navController.navigate("LockerConfirmCustomer")
    }
}