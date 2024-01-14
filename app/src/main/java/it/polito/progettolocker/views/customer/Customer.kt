package it.polito.progettolocker.views.customer

import android.media.Image
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.R
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardsJustText
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.FooterTotal
import it.polito.progettolocker.graphic.FooterWarning
import it.polito.progettolocker.graphic.HeaderX
import it.polito.progettolocker.views.delivery.Locker

@Composable
fun Customer(mainActivity: MainActivity, navController: NavController){

   /* Column(modifier=Modifier.padding(start=25.dp, top=25.dp)
    ) {
        Text("ZARA" , fontWeight = FontWeight.Bold,fontSize = 70.sp )
        Text("LCKR", fontWeight = FontWeight.Bold,fontSize = 70.sp )
    }*/
      Column (
          Modifier
              .background(Color.Magenta)){
              Image(painter = painterResource(id= R.drawable.zara_home), contentDescription ="ImmagineProdotto",
                  modifier= Modifier.fillMaxWidth() .fillMaxHeight(), contentScale = ContentScale.Crop)

              /*Column(modifier=Modifier.padding(start=25.dp, top=25.dp)
              ) {
                  Text("ZARA", fontWeight = FontWeight.Bold, fontSize = 70.sp)
                  Text("LCKR", fontWeight = FontWeight.Bold, fontSize = 70.sp)
              }*/

      }
    FooterHome(navController = navController)











    //Catalogo(mainActivity = mainActivity, navController = navController)
    //Carrello(mainActivity = mainActivity, navController = navController)
    //FooterTotal(price="10.00",navController = navController)
    //FooterDoubleBlack(price = 30,navController = navController)
    //FooterWarning(text = "IL TUO ACQUISTO È ANDATO A BUON FINE!",navController = navController)
    //HeaderX(text = "ciao", navController = navController)
    //con freccia indietro si esce dall'applicazione
    //LockerCode(mainActivity = mainActivity, navController = navController)

    BackHandler (enabled = true){
        mainActivity.finish()
    }
}