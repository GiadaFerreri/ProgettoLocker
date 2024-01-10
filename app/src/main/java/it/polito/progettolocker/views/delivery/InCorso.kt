package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.ui.theme.helveticaFont

@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    val (firstTry, setFirstTryDone) = remember {
        mutableStateOf(true)
    }

    Column{
        Row{
            HeaderDouble(
                text1 = "DA EFFETTUARE",
                weight1 = FontWeight.Normal,
                text2 = "IN CORSO",
                weight2 = FontWeight.Bold,
                onClickHandler1 = { navController.navigate("DaEffettuare") },
                onClickHandler2 = {},
                navController = navController
            )

        }
        Row{
            //TODO: creare le card dalla lista di spedizioni prese in carico
            CardOrder(
                orderNumber = "1E67",
                description = "CONSEGNA AL LOCKER DA EFFETTUARE ENTRO LE 16:00",
                leftButtonText = "CONSEGNA IL PACCO",
                mainActivity = mainActivity,
                navController = navController,
                onClickDestination = "Locker",

            )
        }

    }


    BackHandler (enabled = true){
        navController.navigate("DaEffettuare")
    }

}