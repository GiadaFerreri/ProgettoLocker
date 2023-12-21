package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWarning(text: String, mainActivity: MainActivity, navController: NavController){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE4EBF4)),
        border = BorderStroke(0.5.dp, Color.Black),
        shape= RectangleShape,
        modifier = Modifier
            .padding(30.dp,30.dp,30.dp,0.dp)
    ){
        Row{
            Column(
                //modifier = Modifier.width(60.dp)
            ) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "Informazione",
                    tint = Color.Black,
                    modifier = Modifier.padding(16.dp,16.dp,16.dp,16.dp)
                )
            }
            Column(
            ){
                Row{
                    Text(
                        "ATTENZIONE!",
                        modifier = Modifier.padding(0.dp,16.dp,16.dp,16.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
                Row{
                    Text(
                        text,
                        modifier = Modifier.padding(0.dp,0.dp,16.dp,16.dp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}