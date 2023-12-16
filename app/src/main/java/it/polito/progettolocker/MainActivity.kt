package it.polito.progettolocker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.polito.progettolocker.Views.Customer
import it.polito.progettolocker.Views.DaEffettuare
import it.polito.progettolocker.Views.Delivery
import it.polito.progettolocker.Views.InCorso
import it.polito.progettolocker.Views.Locker
import it.polito.progettolocker.ui.theme.ProgettoLockerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgettoLockerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 Navigation(mainActivity = this)
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Navigation(mainActivity: MainActivity){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomePage"){
        composable("HomePage"){
            HomePage(mainActivity, navController)
        }
        composable("Delivery"){
            Delivery(mainActivity, navController)
        }
        composable("Customer"){
            Customer(mainActivity, navController)
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
    }
}

@Composable
fun HomePage( mainActivity: MainActivity, navController: NavController) {
    Row {
        Button(onClick = {navController.navigate("Delivery")},
            modifier = Modifier
                .padding(3.dp)) {
            Text(text = "Delivery")
        }
        Button(onClick = {navController.navigate("Customer", )}, modifier = Modifier) {
        Text(text = "Customer")
    }
    }


}

