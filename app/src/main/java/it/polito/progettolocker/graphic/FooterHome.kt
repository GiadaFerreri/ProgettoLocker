package it.polito.progettolocker.graphic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterHome(navController: NavController){
    Column(verticalArrangement = Arrangement.Bottom) {
        Row{
            Divider(color = Color.Black, thickness = 1.dp)
        }
        Row(modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(0.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    if(navController.currentDestination?.route != "Customer") navController.navigate("Customer")
                }) {
                Text(
                    text = "HOME",
                    color = Color.Black,
                    fontWeight = if(navController.currentDestination?.route == "Customer") FontWeight.Bold else FontWeight.Normal
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(0.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    if(navController.currentDestination?.route != "Catalogo") navController.navigate("Catalogo")
                }) {
                Text(
                    text = "CATALOGO",
                    color = Color.Black,
                    fontWeight = if(navController.currentDestination?.route == "Catalogo") FontWeight.Bold else FontWeight.Normal
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(0.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    if(navController.currentDestination?.route != "Carrello") navController.navigate("Carrello")
                }) {
                Text(
                    text = "CARRELLO",
                    color = Color.Black,
                    fontWeight = if(navController.currentDestination?.route == "Carrello") FontWeight.Bold else FontWeight.Normal
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(0.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    if(navController.currentDestination?.route != "SpedizioniInCorso") navController.navigate("SpedizioniInCorso")
                }) {
                Text(
                    text = "SPEDIZIONI",
                    color = Color.Black,
                    fontWeight = if(navController.currentDestination?.route == "SpedizioniInCorso") FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}