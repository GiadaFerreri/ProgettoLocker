package it.polito.progettolocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.polito.progettolocker.ui.theme.ProgettoLockerTheme

class MainActivity : ComponentActivity() {
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
    }
}

@Composable
fun HomePage( mainActivity: MainActivity, navController: NavController) {
    Row {
        Button(onClick = {navController.navigate("Delivery")}, modifier = Modifier) {
            Text(text = "Delivery")
        }
        Button(onClick = {navController.navigate("Customer", )}, modifier = Modifier) {
        Text(text = "Customer")
    }
    }


}

