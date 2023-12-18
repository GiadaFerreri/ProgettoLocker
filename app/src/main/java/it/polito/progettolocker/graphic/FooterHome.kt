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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterHome(navController: NavController){
    //TODO: implementare navigator
    Column(verticalArrangement = Arrangement.Bottom) {
        Row{
            Divider(color = Color.Black, thickness = 1.dp)
        }
        Row(modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), modifier = Modifier.padding(0.dp), onClick = { }, contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "HOME",
                    color = Color.Black
                )
            }
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), modifier = Modifier.padding(0.dp), onClick = { }, contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "CATALOGO",
                    color = Color.Black,
                )
            }
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), modifier = Modifier.padding(0.dp), onClick = { }, contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "CARRELLO",
                    color = Color.Black,
                )
            }
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), modifier = Modifier.padding(0.dp), onClick = { }, contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "SPEDIZIONI",
                    color = Color.Black,
                )
            }
        }
    }
}