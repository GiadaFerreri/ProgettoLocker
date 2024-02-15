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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun FooterTotal(price: Int, navController: NavController) {
    //inutile
    var showFooter by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }

    // Coroutine per far scomparire il popup dopo qualche secondo
    LaunchedEffect(openDialog) {
        if (openDialog) {
            delay(3000) // 3000 millisecondi (3 secondi)
            showFooter = false
            openDialog = false

        }
    }
    if(showFooter) {
        Column(verticalArrangement = Arrangement.Bottom) {
            Divider(color = Color.Black, thickness = 0.8.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "TOTALE",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 10.dp),
                            fontWeight = FontWeight.Bold

                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$price EUR",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(end = 10.dp),

                    )
                    Text(
                        text = "IVA INCLUSA, SPEDIZIONE INCLUSA",
                        modifier = Modifier.padding(end = 10.dp),

                            fontSize = 10.sp

                    )
                }

            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = { openDialog = true },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "ACQUISTA",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                }

            }
        }
    }

}