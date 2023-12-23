package it.polito.progettolocker.graphic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderDouble(
    text1: String,
    weight1: FontWeight,
    text2: String,
    weight2: FontWeight,
    onClickHandler1: () -> Unit,
    onClickHandler2: () -> Unit,
    navController: NavController
){
    Column(verticalArrangement = Arrangement.Top){
        Row(modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = { onClickHandler1() } ) {
                Text(
                    text= text1,
                    color = Color.Black,
                    fontWeight = weight1,
                    fontStyle = FontStyle.Normal
                )
            }
            Button(colors = ButtonDefaults.buttonColors(Color.Transparent), onClick = { onClickHandler2() }) {
                Text(
                    text= text2,
                    color = Color.Black,
                    fontWeight = weight2
                )
            }
        }



            Row() {
                Divider(color = Color.Black, thickness = 0.5.dp)
            }
        }
    }
