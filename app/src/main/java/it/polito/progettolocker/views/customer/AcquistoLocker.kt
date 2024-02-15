package it.polito.progettolocker.views.customer

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.Compartment
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Locker
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.CardPurchase
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.HeaderX


@Composable
//Prima pagina acquisto
fun AcquistoLocker(mainActivity: MainActivity, navController: NavController) {

    val lockerList = mutableListOf<Locker>()

    val lockerState = mainActivity.viewModel.lockerState

    fun writeLocker(){

        val v1 = Compartment(idvano = 1, chiuso = true, type = "big", inuso = true)
        val v2 = Compartment(idvano = 2, chiuso = true, type = "small", inuso = true)
        val v3 = Compartment(idvano = 3, chiuso = true, type = "small", inuso = true)
        val vani = mutableListOf<Compartment>(v1,v2,v3)

        val posizione = mutableMapOf<String,String>()
        posizione.put("lat","45.070951")
        posizione.put("long","7.684389")

        val macaddress = "10-33-74-4C-7F-1D"

        val locker = Locker(name = "CASTELLO", address = "P.ZA CASTELLO 153, 10122 TORINO", time = "APERTO 24H SU 24", compartments = vani, macaddress = macaddress, position = posizione)

        mainActivity.viewModel.db.child("Locker").child("castello").setValue(locker)
    }

    //writeLocker()

    Firebase.database.getReference("Locker")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for(snapshot in snapshot.children){
                    val lockerTemp = snapshot.getValue(Locker::class.java)
                    lockerList.add(lockerTemp!!)
                }
                lockerState.value = DataState.Success(lockerList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    Column (){
        Row {
            HeaderX(text = "ACQUISTO", navController =navController, onClickDestination = "Carrello")
        }

        when (val result = lockerState.value) {
            is DataState.Loading -> {
                Box() {
                    CircularProgressIndicator()
                }
            }

            is DataState.Success -> {
                var counterVani = 0
                var vaniOccupati = 0
                var lockers = result.data as MutableList<Locker>
                for (locker in lockers){
                    for (vano in locker.compartments!!) {
                        if (vano.inuso!!) vaniOccupati++
                    }
                    counterVani += locker.compartments!!.size
                }

                if (counterVani == vaniOccupati) {
                    AcquistoLockerOccupied(
                        mainActivity = mainActivity,
                        navController = navController
                    )
                } else {
                    Row(modifier=Modifier.padding(start=30.dp,top=30.dp,end=30.dp, bottom=5.dp)){
                        Text("SELEZIONA IL LOCKER IN CUI RITIRARE IL TUO ORDINE:", fontSize = 15.sp, fontWeight = FontWeight.Medium )
                    }
                    LazyColumn {
                        items(result.data as List<Locker>) { locker ->
                            CardPurchase(
                                locker = locker,
                                lockerLocation = "LOCKER ${locker.name}",
                                description = "${locker.address}\n${locker.time}",
                                mainActivity = mainActivity,
                                navController = navController
                            )
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

            /*LazyColumn {
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO ${numero.value}",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
                item {
                    CardPurchase(
                        lockerLocation = "LOCKER LINGOTTO",
                        description = "VIA NIZZA 294, 10126 TORINO\nAPERTO 24H SU 24",
                        mainActivity = mainActivity,
                        navController = navController)
                }
            }*/
        }


    BackHandler (enabled = true){
        navController.navigate("Carrello")
    }

}