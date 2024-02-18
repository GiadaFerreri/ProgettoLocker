package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.ui.theme.helveticaFont
import it.polito.progettolocker.views.customer.SpedizioniInCorso
import it.polito.progettolocker.views.customer.StoricoConsegne

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            HeaderDouble(
                text1 = "DA EFFETTUARE",
                weight1 = FontWeight.Normal,
                text2 = "IN CORSO",
                weight2 = FontWeight.Bold,
                onClickHandler1 = { navController.navigate("DaEffettuare") },
                onClickHandler2 = {},
                navController = navController
            )
        },
        bottomBar = {
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

        }

    }
    Column {
        CardOrder(
            orderNumber = "1E67",
            description = "CONSEGNA AL LOCKER DA EFFETTUARE ENTRO LE 16:00",
            leftButtonText = "CONSEGNA IL PACCO",
            mainActivity = mainActivity,
            navController = navController,
            onClickDestination = "Locker",

            )
    }


    BackHandler (enabled = true){
        navController.navigate("DaEffettuare")
    }

}