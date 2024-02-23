package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import it.polito.progettolocker.R

@Composable
//Card storico consegne
fun CardProduct(dataRitiro: String, descrizioneProdotto: String, image: String) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            Color(0xFFF8F6F6)
        ),
        border = BorderStroke(0.5.dp, Color.Black),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,30.dp,30.dp,0.dp)
            .shadow(elevation = 4.dp, spotColor = Color.Black, ambientColor =Color.Black)
    )

    {
        Column(verticalArrangement = Arrangement.Top) {
            Row( modifier = Modifier
                //.fillMaxWidth()
                .height(35.dp)
                //.background(Color.Yellow)
                .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Bottom
                ) {
                Text(
                    text = dataRitiro,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    /*style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF000000),
                    )*/
                )
            }
            Row(
                modifier = Modifier
                    .height(160.dp)
                    .padding(16.dp)
                    //.background(Color.Cyan)
                   // .fillMaxWidth()
            ) {

                AsyncImage(
                    model = image,
                    contentDescription = "Immagine prodotto",
                    modifier = Modifier
                        //.size(180.dp)
                        .align(Alignment.CenterVertically)
                            .border(0.5.dp, Color.Black)
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
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            /*style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Left
                            )*/

                        )
                    }
                }
            }


        }

    }
}