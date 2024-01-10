package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.HeaderX

@Composable
//Prima pagina locker
fun LockerConfirmCustomer(mainActivity: MainActivity, navController: NavController){
    Row{
        HeaderX(text ="LOCKER" , navController = navController, onClickDestination = "DaEffettuare")
    }

    Column( verticalArrangement = Arrangement.spacedBy(16.dp),horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top=200.dp)){

        Text(text="CONFERMI DI ESSERE DAVANTI AL LOCKER?",fontSize = 15.sp,color = Color(0xFF000000))
        Column (horizontalAlignment = Alignment.Start, modifier = Modifier
            .padding(start = 50.dp)
            .fillMaxWidth()){
            Text(text="ZARA LCKR", fontWeight = FontWeight.ExtraBold,fontSize = 15.sp,color = Color(0xFF000000))
            Text(text="LOCKER LINGOTTO",fontSize = 12.sp,color = Color(0xFF000000) )
            Text(text="VIA NIZZA 294, 10126 TORINO",fontSize = 12.sp,color = Color(0xFF000000) )
        }
        Row(modifier=Modifier.padding(top=70.dp)) {
            Buttons(text = "CONFERMA", onClickHandler = {navController.navigate("LockerCode")})
        }
        Row(modifier=Modifier.padding(top=200.dp)) {
            CardWarning(text = "ATTENZIONE!\n" +
                    "La conferma farà comparire il codice segreto di apertura sul Locker selezionato.\n" +
                    "Assicurati di essere realmente davanti al Locker.", mainActivity = mainActivity, navController =navController )

        }
    }
    BackHandler (enabled = true){
        navController.navigate("SpedizioniInCorso")
    }

}