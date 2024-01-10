package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity

@Composable
fun CardOrder(
    orderNumber: String,
    description: String,
    leftButton: Boolean = true,
    rightButton: Boolean = false,
    leftButtonText: String = "VEDI ORDINE",
    rightButtonText: String = "RITIRA IL PACCO",
    mainActivity: MainActivity,
    navController: NavController,
    onClickDestination: String = "",
    onClickDestination2: String =""
){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xFFF8F6F6)),
        border = BorderStroke(0.5.dp, Color.Black),
        shape= RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,30.dp,30.dp,0.dp)
    ){
        Column {
            Row {
                Text(
                    "ORDINE #$orderNumber",
                    modifier = Modifier.padding(16.dp,16.dp,16.dp,16.dp),
                    fontWeight = FontWeight.Medium
                )
            }
            Row {
                Text(
                    description,
                    modifier = Modifier.padding(16.dp,0.dp,16.dp,32.dp),
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(0.dp,0.dp,32.dp,0.dp)
                ) {
                    if(leftButton){
                        Buttons(leftButtonText,onClickHandler = {if(onClickDestination != "")  navController.navigate(onClickDestination) })
                    }
                }
                Column (
                    horizontalAlignment = Alignment.End
                ){
                    if(rightButton){
                        Buttons(rightButtonText, onClickHandler = {if(onClickDestination2 != "") navController.navigate(onClickDestination2) })
                    }
                }
            }
        }
    }
}