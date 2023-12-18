package it.polito.progettolocker.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.polito.progettolocker.Graphic.Buttons
import it.polito.progettolocker.Graphic.CardsJustText
import it.polito.progettolocker.Graphic.HeaderDouble
import it.polito.progettolocker.Graphic.HeaderX
import it.polito.progettolocker.MainActivity

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Delivery(mainActivity: MainActivity, navController: NavController){
    DaEffettuare(mainActivity, navController)
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DaEffettuare(mainActivity: MainActivity, navController: NavController){
    HeaderDouble(
        text1 = "DA EFFETTUARE",
        weight1 = FontWeight.Bold,
        text2 = "IN CORSO",
        weight2 = FontWeight.Normal,
        onClickListener2 = "InCorso",
        navController = navController
    )




}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){
    HeaderDouble(
        text1 = "DA EFFETTUARE",
        weight1 = FontWeight.Normal,
        text2 = "IN CORSO",
        weight2 = FontWeight.Bold,
        onClickListener1 = "DaEffettuare",
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
}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Locker(mainActivity: MainActivity, navController: NavController) {
    Column(  modifier = Modifier
        .fillMaxWidth()){
        Row {
            HeaderX(text = "LOCKER", navController = navController, onClickListener = "DaEffettuare")

        }
        Row ( modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly)
        {
            CardsJustText(text1 = "IL TUO PACCO È STATO DEPOSITATO CORRETTAMENTE?")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Buttons(text = "CONFERMA")
            Buttons(text = "RIAPRI IL CASSETTO")
        }

    }

    }

