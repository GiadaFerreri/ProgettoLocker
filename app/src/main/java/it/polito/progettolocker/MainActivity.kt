package it.polito.progettolocker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
import it.polito.progettolocker.views.customer.Customer
import it.polito.progettolocker.views.delivery.DaEffettuare
import it.polito.progettolocker.views.delivery.Delivery
import it.polito.progettolocker.views.delivery.InCorso
import it.polito.progettolocker.views.delivery.Locker
import it.polito.progettolocker.dataClass.DeliveryMan
import it.polito.progettolocker.dataClass.User
import it.polito.progettolocker.ui.theme.ProgettoLockerTheme

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.views.customer.Acquisto
import it.polito.progettolocker.views.customer.AcquistoLocker
import it.polito.progettolocker.views.customer.AcquistoLockerCompleto
import it.polito.progettolocker.views.customer.Carrello
import it.polito.progettolocker.views.customer.Catalogo
import it.polito.progettolocker.views.customer.DettagliOrdine
import it.polito.progettolocker.views.customer.LockerCode
import it.polito.progettolocker.views.customer.LockerConfirmCustomer
import it.polito.progettolocker.views.customer.LockerCorrectedCode
import it.polito.progettolocker.views.customer.LockerDysfunction
import it.polito.progettolocker.views.customer.Spedizioni
import it.polito.progettolocker.views.customer.SpedizioniInCorso
import it.polito.progettolocker.views.customer.StoricoConsegne
import it.polito.progettolocker.views.delivery.LockerConfirm
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private val viewModel: ViewModelLocker by lazy {
        val factory = ViewModelLockerFactory(auth, database)
        ViewModelProvider(this, factory).get(ViewModelLocker::class.java)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var eventListener: ValueEventListener
    private lateinit var database: DatabaseReference
    private lateinit var user: User
    private lateinit var deliveryMan: DeliveryMan

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        database =
            Firebase.database("https://locker-53147-default-rtdb.europe-west1.firebasedatabase.app/").reference

        setContent {
            ProgettoLockerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                  //  val systemUiController = rememberSystemUiController()

                    SideEffect {
                      //  systemUiController.setStatusBarColor(color = Color.White)
                    }

                    Navigation(mainActivity = this)


                }
            }
        }
    }
}

@Composable
fun Navigation(mainActivity: MainActivity){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "AnimatedLauncherIcon"){
        composable("AnimatedLauncherIcon"){
            AnimatedLauncherIcon(mainActivity,navController)
        }

        composable("HomePage"){
            HomePage(mainActivity, navController)
        }

        //NAVIGAZIONE DELIVERY
        composable("Delivery"){
            Delivery(mainActivity, navController)
        }
        composable("DaEffettuare"){
           DaEffettuare(mainActivity, navController)
        }
        composable("InCorso"){
            InCorso(mainActivity, navController)
        }
        composable("Locker"){
            Locker(mainActivity, navController)
        }
        composable("LockerConfirm"){
            LockerConfirm(mainActivity, navController)
        }

        //NAVIGAZIONE CUSTOMER

        composable("Customer"){
            Customer(mainActivity, navController)
        }
        composable("Carrello"){
            Carrello(mainActivity, navController)
        }
        composable("Catalogo"){
            Catalogo(mainActivity, navController)
        }
        composable("AcquistoLocker"){
            AcquistoLocker(mainActivity, navController)
        }
        composable("Acquisto"){
            Acquisto(mainActivity, navController, price=89)
        }
        composable("LockerDysfunction"){
            AcquistoLockerCompleto(mainActivity, navController)
        }
        composable("Spedizioni"){
            Spedizioni(mainActivity, navController)
        }
        composable("SpedizioniInCorso"){
            SpedizioniInCorso(mainActivity, navController)
        }
        composable("StoricoConsegne"){
            StoricoConsegne(mainActivity, navController)
        }
        composable("DettagliOrdine"){
            DettagliOrdine(mainActivity, navController)
        }
        composable("LockerConfirmCustomer"){
          LockerConfirmCustomer(mainActivity, navController)
        }
        composable("LockerCode"){
            LockerCode(mainActivity, navController)
        }
        composable("LockerDysfunction"){
            LockerDysfunction(mainActivity, navController)
        }
        composable("LockerCorrectedCode"){
            LockerCorrectedCode(mainActivity, navController)
        }








        /*navigation(startDestination = "DaEffettuare", route = "DaEffettuare") {

            composable("DaEffettuare") {
                DaEffettuare(mainActivity, navController) }
            composable("InCorso") {
                InCorso(mainActivity, navController)
            }
        }
*/
    }
}

/*
fun NavGraphBuilder.loginGraph(mainActivity: MainActivity, navController: NavController) {
    navigation(startDestination = "Delivery", route = "Delivery") {

        composable("Delivery") {
            Delivery(mainActivity, navController)
        }
        composable("DaEffettuare") {
            DaEffettuare(mainActivity, navController)
        }
        composable("InCorso") {
            InCorso(mainActivity, navController)
        }

    }
}

*/



@Composable
fun HomePage(mainActivity: MainActivity, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navController.navigate("Delivery") },
            shape= RectangleShape,
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .border(width = 0.5.dp, color = Color.Black)
                .width(116.dp)
                .height(45.dp)
                .background(color = Color(0xFFF8F6F6)),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.Transparent)
        ){
            Text(text = "DELIVERY", fontWeight = FontWeight.Normal)
        }
        Button(
            onClick = {navController.navigate("Customer") },
            shape= RectangleShape,
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .border(width = 0.5.dp, color = Color.Black)
                .width(116.dp)
                .height(45.dp)
                .background(color = Color(0xFFF8F6F6)),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.Transparent)
        ){
            Text(text = "CUSTOMER", fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun AnimatedLauncherIcon(mainActivity: MainActivity, navController: NavController) {
   val size= remember {
       androidx.compose.animation.core.Animatable(0.5f)
   }
    LaunchedEffect(true) {
        size.animateTo(1f,
            animationSpec = tween(1500, easing = LinearEasing)
        )
        delay(1500)
        navController.navigate("HomePage")
    }


/*
    val size by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.titolo),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .scale(size.value)
        )
    }

}


