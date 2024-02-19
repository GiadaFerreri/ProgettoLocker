package it.polito.progettolocker.graphic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States

@Composable
fun CardOrder(
    shipping: Shipping = Shipping(),
    orderNumber: String,
    description: String,
    leftButton: Boolean = true,
    rightButton: Boolean = false,
    leftButtonText: String = "VEDI DETTAGLI",
    rightButtonText: String = "RITIRA IL PACCO",
    mainActivity: MainActivity,
    navController: NavController,
    onClickDestination: String = "",
    onClickDestination2: String ="",
    toHandle: Boolean = false
){
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xFFF8F6F6)),
        border = BorderStroke(0.5.dp, Color.Black),
        shape= RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,30.dp,30.dp,0.dp)
            .shadow(elevation = 4.dp, spotColor = Color.Black, ambientColor =Color.Black)
    ){
        Column {
            Row {
                Text(
                    "ORDINE #$orderNumber",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(16.dp,16.dp,16.dp,16.dp),
                    fontWeight = FontWeight.Medium
                )
            }
            Row {
                Text(
                    description,
                    modifier = Modifier.padding(16.dp,0.dp,16.dp,32.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(0.dp,0.dp,32.dp,0.dp)
                ) {
                    if(leftButton){
                        Button(
                            onClick = {
                                mainActivity.viewModel.selectedShipping.value = shipping
                                if(toHandle)
                                    mainActivity.viewModel.db.child("Shipping/${shipping.shippingId}/state").setValue(States.HANDLED)
                                navController.navigate(onClickDestination)
                                mainActivity.shippingId=orderNumber
                                      },
                            shape= RectangleShape,
                            modifier = Modifier
                                .shadow(elevation = 4.dp, spotColor = Color.Black, ambientColor = Color.Black)
                                .border(width = 0.5.dp, color = Color.Black)
                                .width(140.dp)
                                .height(50.dp)
                                .background(color = Color(0xFFF8F6F6)),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = leftButtonText,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 12.sp
                            )
                        }
                    }
                }
                Column (
                    horizontalAlignment = Alignment.End
                ){
                    if(rightButton){
                        mainActivity.viewModel.selectedShipping.value = shipping
                        Buttons(rightButtonText, onClickHandler = {if(onClickDestination2 != "") navController.navigate(onClickDestination2) })
                    }
                }
            }
        }
    }
}