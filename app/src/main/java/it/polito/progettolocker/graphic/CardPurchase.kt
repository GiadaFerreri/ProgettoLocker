package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Locker

@Composable
fun CardPurchase(
    locker: Locker,
    lockerLocation: String,
    description: String,
    mainActivity: MainActivity,
    navController: NavController) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F6F6)
        ),
        border = BorderStroke(0.5.dp, Color.Black),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 30.dp, 30.dp, 0.dp)
            .shadow(elevation = 4.dp, spotColor = Color.Black, ambientColor = Color.Black)
    ) {
        Column() {
            Row (){
                Text(
                    text = lockerLocation,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 16.dp),
                    fontWeight = FontWeight.Medium
                )
            }
            Row (){
                Text(
                    description,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 32.dp),
                    fontWeight = FontWeight.Normal
                )
            }
            Column (Modifier .fillMaxWidth(), horizontalAlignment = Alignment.End){
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    var counter = 0
                    for(vano in locker.compartments!!){
                        if(vano.inuso!!) counter++
                    }
                    if(counter == locker.compartments!!.size){
                        Text(text = "LOCKER AL COMPLETO", fontWeight = FontWeight.Bold)
                    }
                    else{
                        Buttons("SELEZIONA", onClickHandler = { navController.navigate("Acquisto") })
                    }
                }
            }
        }
    }
}