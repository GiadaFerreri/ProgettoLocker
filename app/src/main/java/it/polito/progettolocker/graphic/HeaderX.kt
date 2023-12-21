package it.polito.progettolocker.graphic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderX(text: String,
            onClickDestination: String = "",
            navController: NavController,

){

    Column(verticalArrangement = Arrangement.Top){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (horizontalAlignment = Alignment.Start) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = { if(onClickDestination != "") navController.navigate(onClickDestination) }
                ){
                    Icon(Icons.Filled.Close, contentDescription = "Chiudi", tint = Color.Black)
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(colors = ButtonDefaults.buttonColors(Color.Transparent),onClick = { /*TODO*/ }
                ){
                    Text(modifier= Modifier
                        //.width(83.dp)
                        .height(30.dp),
                        text = text,
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 20.sp,
                        )
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End
            ){
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = { }
                ){
                    Icon(Icons.Filled.Close, contentDescription = "Chiudi", tint = Color.Transparent)
                }
            }




        }

        Divider(color = Color.Black, thickness = 0.5.dp)
    }

}


