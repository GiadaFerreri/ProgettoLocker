package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Prima pagina del locker
fun Locker(mainActivity: MainActivity, navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            HeaderX(text ="LOCKER" , navController = navController, onClickDestination = "DaEffettuare")
        },
        bottomBar = {
            Row(modifier = Modifier.padding(bottom = 50.dp)) {
                CardWarning(
                    text = "La conferma comporta l'apertura dello sportello del Locker.",
                    mainActivity = mainActivity,
                    navController = navController
                )

            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 70.dp)
            ) {
                Row(
                    modifier = Modifier.padding(start = 35.dp, end = 35.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "CONFERMI DI ESSERE DAVANTI AL LOCKER?",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = Color(0xFF000000)
                    )

                }
                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .padding(start = 35.dp, top = 20.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "ZARA LCKR",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        color = Color(0xFF000000)
                    )
                    Text(text = "LOCKER LINGOTTO", fontSize = 15.sp, color = Color(0xFF000000))
                    Text(
                        text = "VIA NIZZA 294, 10126 TORINO",
                        fontSize = 15.sp,
                        color = Color(0xFF000000)
                    )
                }
                Row(modifier = Modifier.padding(top = 50.dp)) {
                    Buttons(
                        text = "CONFERMA",
                        onClickHandler = {
                            val shipping = mainActivity.viewModel.selectedShipping.value
                            //Apre il cassetto
                            mainActivity.viewModel.db.child("Locker/${shipping.lockerId}/compartments/${shipping.compartmentId}/chiuso")
                                .setValue(false)
                            navController.navigate("LockerConfirm")
                        })
                }
                /* Row(modifier = Modifier.padding(top = 100.dp)) {

                 }*/
            }
        }


    }


    BackHandler (enabled = true){
        navController.navigate("DaEffettuare")
    }

}