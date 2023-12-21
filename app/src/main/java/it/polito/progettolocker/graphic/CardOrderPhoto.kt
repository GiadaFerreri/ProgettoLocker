package it.polito.progettolocker.graphic

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.R

@Composable
//Card pagina storico consegne???
fun CardOrderPhoto(navController: NavController, textProduct: String, price: Float) {
   Column(verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Image(painter = painterResource(id= R.drawable.zara_product1), contentDescription ="ImmagineProdotto",
                  modifier= Modifier
                      .border(0.5.dp,Color.Black))
            Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(300.dp)
                        .height(170.dp)
                        .padding(10.dp))
            {
                Row(){
                Text(
                    text = "ZW COLLECTION SEQUINNED VELVET BLAZER\n", //textproduct
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )

                )
            }
                Row(){

                    Text(
                        text =price.toString()+" EUR", //price
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )

                    )
                }
                Row(){ Icon(Icons.Filled.AddCircle, contentDescription = "+", tint = Color.DarkGray)}
                }

            }
       Row() {
           Divider(color = Color.LightGray, thickness = 0.5.dp)
       }

        }

    }
