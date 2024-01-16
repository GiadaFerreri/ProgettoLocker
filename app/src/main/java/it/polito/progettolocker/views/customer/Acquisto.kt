package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardPhotoSwipe
import it.polito.progettolocker.graphic.FooterTotal
import it.polito.progettolocker.graphic.FooterWarning
import it.polito.progettolocker.graphic.HeaderX
import kotlinx.coroutines.delay

@Composable
//seconda pagina acquisto
fun Acquisto(mainActivity: MainActivity, navController: NavController, price: Int){
    var openButton by remember { mutableStateOf(true) }
    var showFooter by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }

    Column (){
        Row(){
            HeaderX(text = "ACQUISTO", navController = navController, onClickDestination = "Carrello")
        }
        Row (modifier = Modifier.padding(16.dp)){
            Text(text = "3 ARTICOLI",fontSize = 12.sp)
        }
        Row {
            CardPhotoSwipe(navController = navController)
        }
        Row (){
            Text(
                text = "ZARA LCKR",
                fontSize = 15.sp,
                modifier = Modifier.padding(16.dp,5.dp),
                fontWeight = FontWeight.Medium
            )
        }
        Row (Modifier.fillMaxWidth()){
            Column (Modifier.weight(2f)){
                Text(
                    text = "LOCKER LINGOTTO\nVIA NIZZA 294, 10126 TORINO",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(16.dp, 5.dp))
            }
            if(openButton){
                Column(Modifier.weight(1f).padding(end=10.dp)
                ) {
                    Buttons("MODIFICA",
                        onClickHandler = { navController.navigate("AcquistoLocker",) })
                }
            }

        }
    }

    // Coroutine per far scomparire il popup dopo qualche secondo
    LaunchedEffect(openDialog) {
        if (openDialog) {
            delay(3000) // 3000 millisecondi (3 secondi)
            showFooter = false
            openDialog = false
            openButton =false

        }
    }
    if(showFooter) {
        Column(verticalArrangement = Arrangement.Bottom) {
            Divider(color = Color.Black, thickness = 0.5.dp)
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
                        color = Color.Black,
                        modifier = Modifier.padding(start = 5.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$price EUR",
                        modifier = Modifier.padding(end = 5.dp),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                    Text(
                        text = "IVA INCLUSA, SPEDIZIONE INCLUSA",
                        modifier = Modifier.padding(end = 5.dp),
                        style = TextStyle(
                            fontSize = 6.sp
                        )
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

    if(openDialog){
        FooterWarning(text = "IL TUO ACQUISTO È ANDATO A BUON FINE!",
            navController = navController)
    }

    BackHandler (enabled = true){
        navController.navigate("AcquistoLocker")
    }

}