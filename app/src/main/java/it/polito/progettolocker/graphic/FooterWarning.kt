package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable

fun FooterWarning(text: String, navController: NavController){
    Column(verticalArrangement = Arrangement.Bottom, modifier=Modifier.fillMaxWidth()) {
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color.White),
            border = BorderStroke(0.5.dp, Color.Black),
            shape= RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(129.dp)
                .padding(horizontal = 1.dp)
                .padding(bottom = 1.dp)

        ){
            Column(verticalArrangement = Arrangement.SpaceBetween, modifier=Modifier.fillMaxHeight()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                   , horizontalArrangement = Arrangement.SpaceBetween) {


                    Text(
                        text = text,
                        modifier = Modifier.padding(20.dp),
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color(0xFF000000),
                        )
                    )

                }
                Row(modifier = Modifier
                    .fillMaxWidth(),horizontalArrangement = Arrangement.End){

                    Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),onClick = { navController.navigate("SpedizioniInCorso")}) {
                        Text(text="VEDI", modifier = Modifier.padding(20.dp),
                            style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),

                        ))
                    }

                }
            }


        }
        }

}