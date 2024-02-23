package it.polito.progettolocker.views.delivery

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.CardOrder
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.HeaderDouble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InCorso(mainActivity: MainActivity, navController: NavController){

    val tempList = mutableListOf<Shipping>()

    mainActivity.viewModel.db.child("Shipping")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for(snapshot in snapshot.children){
                    val shipping = snapshot.getValue(Shipping::class.java)
                    tempList.add(shipping!!)
                }
                mainActivity.viewModel.shippingState.value = DataState.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })



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
            when (val result = mainActivity.viewModel.shippingState.value) {
                is DataState.Loading -> {
                    Box() {
                        CircularProgressIndicator()
                    }
                }

                is DataState.Success -> {
                    Row {
                        LazyColumn(modifier=Modifier.padding(bottom=30.dp)) {
                            items(result.data as List<Shipping>) { shipping ->
                                Row {
                                    if (shipping.state == States.HANDLED) {
                                        CardOrder(
                                            shipping = shipping,
                                            orderNumber = shipping.shippingId!!.toString(),
                                            description = "CONSEGNA AL LOCKER LINGOTTO",
                                            leftButtonText = "CONSEGNA IL PACCO",
                                            mainActivity = mainActivity,
                                            navController = navController,
                                            onClickDestination = "Locker"
                                        )
                                    }

                                }
                            }
                        }
                    }
                }

                is DataState.Failure -> {
                    CardWarning(
                        text = result.message,
                        mainActivity = mainActivity,
                        navController = navController
                    )
                }

                else -> {
                    CardWarning(
                        text = "Error fetching data",
                        mainActivity = mainActivity,
                        navController = navController
                    )
                }

            }
        }

    }

    BackHandler (enabled = true){
        navController.navigate("DaEffettuare")
    }

}