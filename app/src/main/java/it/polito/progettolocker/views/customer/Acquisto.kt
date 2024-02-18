package it.polito.progettolocker.views.customer

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.R
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardPhotoSwipe
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterWarning
import it.polito.progettolocker.graphic.HeaderX
import kotlinx.coroutines.delay

@Composable
//seconda pagina acquisto
fun Acquisto(mainActivity: MainActivity, navController: NavController, price: Int){
    var openButton by remember { mutableStateOf(true) }
    var showFooter by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }

    val cartList = mutableListOf<Article>()
    val cartState = mainActivity.viewModel.cartState
    val userId = mainActivity.userId
    val db = mainActivity.viewModel.db

    mainActivity.viewModel.db.child("Cart").child(userId).child("articles")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                cartList.clear()
                for(snapshot in snapshot.children){
                    val article = snapshot.getValue(Article::class.java)
                    cartList.add(article!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

    fun generateUniqueShippingId(): String {
        var shippingId: String = ""
        // Genera una stringa di 3 numeri casuali
        val randomString = (100..999).random().toString()

        // Crea una query per controllare se la stringa esiste già
        val query = db.child("Shipping/${randomString}")

        // Esegui la query e attendi il risultato
        query.get().addOnSuccessListener {
            // Se la stringa non esiste, salvala nel database e restituiscila
            if (!it.exists()) {
                shippingId = randomString
            } else {
                // Se la stringa esiste già, rigenera una nuova stringa
                generateUniqueShippingId()
            }
        }

        // Restituisce una stringa vuota se si verifica un errore
        return shippingId
    }


    fun newShipping() {
        var shippingId = (100..999).random().toString()
        var depositId = (10000..99999).random().toString()
        var pickupId = (10000..99999).random().toString()
        var lockerId = "lingotto"
        var compartmentId = mainActivity.viewModel.vanoDaUsare.value.toString()

        var shipping =
            Shipping(
                shippingId = shippingId,
                userId = userId,
                deliverymanId = "fattorino",
                state = States.PENDING,
                articles = cartList as List<Article>,
                pickupId = pickupId,
                depositId = depositId,
                lockerId = lockerId,
                compartmentId = compartmentId
            )

        db.child("Shipping").child("${shippingId}").setValue(shipping)
        db.child("Cart").child(userId).child("articles").removeValue()
        db.child("Locker").child("${lockerId}/compartments/${compartmentId}/inuso").setValue(true)
    }

    Column (){
        Row(){
            HeaderX(text = "ACQUISTO", navController = navController, onClickDestination = "Carrello")
        }
        Row {
            when (val result = mainActivity.viewModel.cartState.value) {
                is DataState.Loading -> {
                    Box() {
                        CircularProgressIndicator()
                    }
                }

                is DataState.Success -> {
                        LazyRow {
                            items(result.data as List<Article>) { article ->
                                if (article.quantity!!.toFloat() > 0) {
                                    Column(verticalArrangement = Arrangement.Top) {
                                        Column(modifier = Modifier.padding(top = 5.dp)) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(2.dp)
                                            ) {
                                                AsyncImage(
                                                    model = article.image!!,
                                                    contentDescription = "Immagine prodotto",
                                                    modifier = Modifier
                                                        .border(0.5.dp, Color.Black)
                                                )



                                            }

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

        Row (modifier = Modifier.padding(16.dp)){
            Text(text = "3 ARTICOLI",fontSize = 12.sp)
        }

        Row (){
            Text(
                text = "ZARA LCKR",
                fontSize = 15.sp,
                modifier = Modifier.padding(16.dp,5.dp),
                fontWeight = FontWeight.Medium
            )
        }
        Row (Modifier.fillMaxWidth()){
            Column (Modifier.weight(2f)){
                Text(
                    text = "LOCKER LINGOTTO\nVIA NIZZA 294, 10126 TORINO",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(16.dp, 5.dp))
            }
            if(openButton){
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    Buttons("MODIFICA",
                        onClickHandler = { navController.navigate("AcquistoLocker",) })
                }
            }

        }
    }

    // Coroutine per far scomparire il popup dopo qualche secondo
    LaunchedEffect(openDialog) {
        if (openDialog) {
            delay(3000) // 3000 millisecondi (3 secondi)
            showFooter = false
            openDialog = false
            openButton =false

        }
    }
    if(showFooter) {
        Column(verticalArrangement = Arrangement.Bottom) {
            Divider(color = Color.Black, thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "TOTALE",
                        color = Color.Black,
                        modifier = Modifier.padding(start = 5.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$price EUR",
                        modifier = Modifier.padding(end = 5.dp),
                        style = TextStyle(
                            fontSize = 15.sp
                        )
                    )
                    Text(
                        text = "SPEDIZIONE INCLUSA",
                        modifier = Modifier.padding(end = 5.dp),
                        style = TextStyle(
                            fontSize = 10.sp
                        )
                    )
                }

            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RectangleShape,
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {
                        openDialog = true
                        newShipping()
                              },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "ACQUISTA",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 15.sp,
                        )
                    )
                }

            }
        }
    }

    if(openDialog){
        FooterWarning(text = "IL TUO ACQUISTO È ANDATO A BUON FINE!",
            navController = navController,
            navigation = "Spedizioni")
    }

    BackHandler (enabled = true){
        navController.navigate("AcquistoLocker")
    }

}