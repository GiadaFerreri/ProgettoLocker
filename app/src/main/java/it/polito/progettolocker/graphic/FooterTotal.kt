package it.polito.progettolocker.graphic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FooterTotal(price: String){
    Column(verticalArrangement = Arrangement.Bottom) {
        Divider(color = Color.Black, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start,) {
                Text(
                    text = "TOTALE", color = Color.Black, style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text =price+"EUR", style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Text(
                        text = "IVA INCLUSA, SPEDIZIONE INCLUSA", style = TextStyle(
                fontSize = 6.sp
                )
                )
            }

        }



        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(colors = ButtonDefaults.buttonColors(Color.Black), shape= RectangleShape, modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(60.dp)
                , onClick = {/*TODO */}, contentPadding = PaddingValues(0.dp)) {
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