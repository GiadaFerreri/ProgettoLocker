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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FooterHome(navController: NavController){
    Column(verticalArrangement = Arrangement.Bottom) {
        Row{
            Divider(color = Color.Black, thickness = 0.8.dp)
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
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = if(navController.currentDestination?.route == "Customer") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if(navController.currentDestination?.route == "Customer") TextDecoration.Underline else TextDecoration.None
                    //fontFamily = it.polito.progettolocker.ui.theme.helveticaFont ,
                    //style = if(navController.currentDestination?.route == "Customer") TextStyle(textDecoration = TextDecoration.Underline) else TextStyle(textDecoration = TextDecoration.None)
                    /*modifier = if(navController.currentDestination?.route == "Customer") Modifier.drawBehind {
                        val strokeWidthPx = 1.dp.toPx()
                        val verticalOffset = size.height - 2.sp.toPx()
                        drawLine(
                            color = Color.Red,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, verticalOffset),
                            end = Offset(size.width, verticalOffset)
                        )
                    }else Modifier.drawBehind {  }*/
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
                    fontSize = 15.sp,
                    fontWeight = if(navController.currentDestination?.route == "Catalogo") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if(navController.currentDestination?.route == "Catalogo") TextDecoration.Underline else TextDecoration.None
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
                    fontSize = 15.sp,
                    fontWeight = if(navController.currentDestination?.route == "Carrello") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if(navController.currentDestination?.route == "Carrello") TextDecoration.Underline else TextDecoration.None
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(0.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    if(navController.currentDestination?.route != "Spedizioni") navController.navigate("Spedizioni")
                }) {
                Text(
                    text = "SPEDIZIONI",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = if(navController.currentDestination?.route == "Spedizioni") FontWeight.Bold else FontWeight.Normal,
                    textDecoration = if(navController.currentDestination?.route == "Spedizioni") TextDecoration.Underline else TextDecoration.None
                )
            }
        }
    }
}