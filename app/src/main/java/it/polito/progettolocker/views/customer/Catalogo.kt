package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.FooterHome
@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController){

    Column(verticalArrangement = Arrangement.Top){
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(colors = ButtonDefaults.buttonColors(Color.Transparent),onClick = { /*TODO*/ }
                ){
                    Text(
                        text = "CATALOGO",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
        Divider(color = Color.Black, thickness = 0.5.dp)

    }

  //  Divider(color = Color.Black, thickness = 0.5.dp)
    CardOrderPhoto(navController = navController, textProduct = "ZW COLLECTION SEQUINNED VELVET BLAZER", price = 99.95F)
    FooterHome(navController = navController)

    BackHandler (enabled = true){
        navController.navigate("Customer")
    }

}