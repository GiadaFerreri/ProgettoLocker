package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.polito.progettolocker.R

@Composable
//Card storico consegne
fun CardProduct(dataRitiro: String, descrizioneProdotto: String) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            Color(0xFFF8F6F6)
        ),
        border = BorderStroke(0.5.dp, Color.Black),
        shape = RectangleShape,
        modifier = Modifier
            .size(width = 400.dp, height = 173.dp)
            .padding(10.dp)
    )

    {
        Column(verticalArrangement = Arrangement.Top) {
            Row( modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                //.background(Color.Yellow)
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Bottom
                ) {
                Text(
                    text = dataRitiro,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
            Row(
                modifier = Modifier
                    .height(160.dp)
                    //.background(Color.Cyan)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.zara_product1),
                    contentDescription = "ImmagineProdotto",
                    Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
                )
                Column (modifier = Modifier
                    .width(300.dp)
                    .height(180.dp)
                    .padding(10.dp),
                    //.background(Color.Magenta),
                    verticalArrangement = Arrangement.Center)
                {
                    Row(modifier = Modifier .width(300.dp)) {
                        Text(
                            text = descrizioneProdotto, //textproduct
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Left
                            )

                        )
                    }
                }
            }


        }

    }
}