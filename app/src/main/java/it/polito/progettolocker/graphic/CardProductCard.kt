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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import it.polito.progettolocker.ui.theme.PurpleGrey40

//Card pagina Carrello
@Composable
fun CardProductCard(navController: NavController, textProduct: String, price: Float) {
    Column(verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier
            .fillMaxWidth() .padding(16.dp)) {
            Image(painter = painterResource(id= R.drawable.zara_product1), contentDescription ="ImmagineProdotto",)

            Column (modifier = Modifier
                .width(300.dp)
                .height(180.dp)
                .padding(20.dp))
            {
                Row(){
                    Text(
                        text = textProduct,
                        fontSize = 12.sp
//                        style = TextStyle(
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight(400),
//                            color = Color(0xFF000000),
//                            textAlign = TextAlign.Left
//                        )

                    )
                }
                Row(Modifier.fillMaxWidth()){

                    Text(
                        text = price.toString()+" EUR\n", //price
                        fontSize = 12.sp
//                        style = TextStyle(
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight(400),
//                            color = Color(0xFF000000),
//                            textAlign = TextAlign.Left
//                        )

                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFF000000))
                        .width(160.dp)
                        .height(30.dp))
                {
                    Button(onClick = { /*TODO*/ },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "-",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center)

                            )
                        }

                        Button(onClick = { /*TODO*/ },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            border = BorderStroke(1.dp, Color.Black),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "1",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center)
                            )
                        }

                       Button(onClick = { /*TODO*/ },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "+",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center)

                            )
                        }

                    }

            }

        }

            Row {
                Divider(color = Color.Gray, thickness = 1.dp)
            }
    }



}
