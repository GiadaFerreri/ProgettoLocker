package it.polito.progettolocker.views.delivery

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.HeaderDouble
import it.polito.progettolocker.MainActivity

@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    HeaderDouble(
        text1 = "DA EFFETTUARE",
        weight1 = FontWeight.Normal,
        text2 = "IN CORSO",
        weight2 = FontWeight.Bold,
        onClickDestination1 = "DaEffettuare",
        navController = navController
    )
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly) {

        Button(
            onClick = { navController.navigate("Locker") },
            modifier = Modifier
                .padding(3.dp)
        ) {
            Text("Consegna il pacco")
        }
    }

    BackHandler (enabled = true){
        navController.navigate("DaEffettuare")
    }

}