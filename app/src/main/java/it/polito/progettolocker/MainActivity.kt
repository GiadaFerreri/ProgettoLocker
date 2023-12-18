package it.polito.progettolocker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import it.polito.progettolocker.dataClass.DeliveryMan
import it.polito.progettolocker.dataClass.User
import it.polito.progettolocker.ui.theme.ProgettoLockerTheme

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.ValueEventListener

private lateinit var auth: FirebaseAuth
private lateinit var eventListener: ValueEventListener
private lateinit var database: DatabaseReference
private lateinit var user: User
private lateinit var deliveryMan : DeliveryMan

class MainActivity : ComponentActivity() {

    private val viewModel: ViewModelLocker by viewModels()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        database = Firebase.database("https://locker-53147-default-rtdb.europe-west1.firebasedatabase.app/").reference

        viewModel.WriteInDatabase(database)
        viewModel.WriteBoolInDatabase(database)
        viewModel.WriteIntInDatabase(database)

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

