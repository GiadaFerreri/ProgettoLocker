package it.polito.progettolocker.views.customer

import android.content.ContentValues
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.R
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.Cart
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.FooterWarning
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController) {
    var showFooter by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }

    val userId = "carrelloprova"

    // Coroutine per far scomparire il popup dopo qualche secondo
    LaunchedEffect(openDialog) {
        if (openDialog) {
            delay(3000) // 3000 millisecondi (3 secondi)
            showFooter = true
            openDialog = false
        }
    }

    var cartList = mutableListOf<Cart>()
    var cart = mutableListOf<Article>()
    val cartState = mainActivity.viewModel.cartState

    fun updateCart(){
        mainActivity.viewModel.db.child("Cart").child(userId).child("articles")
            .addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempList = snapshot.getValue<ArrayList<Map<String,Any>>>() as ArrayList<Map<String,Any>>

                    tempList.forEach {
                        cart.add(Article(idArticle = it["idArticle"] as Number, quantity = it["quantity"] as Number, price = it["price"] as Number, name = it["name"] as String, type = it["type"] as String))
                    }
                    cartState.value = DataState.Success(cartList)
                    /*val updatedCart = cartList.filter { it.userId == userId }[0].articles as List<Article>
                    cart.clear()
                    updatedCart.forEach{
                        cart.add(it)
                    }*/

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
    }

    fun addToCart(prodotto: Article, quantita: Int) {
        // Ottieni il carrello dell'utente dal database
        val carrelloRef = mainActivity.viewModel.db.child("Cart/${userId}")

        // Controlla se il prodotto è già presente nel carrello
        val prodottoNelCarrelloRef = carrelloRef.child("articles/${prodotto.idArticle}")
        prodottoNelCarrelloRef.get().addOnSuccessListener {
            if (it.exists()) {
                // Il prodotto è già presente nel carrello, aggiorna la quantità
                val quantitaAttuale = it.child("quantity")
                val quantitaAttualeValue = quantitaAttuale.value
                val nuovaQuantita = (quantitaAttualeValue as Long).toInt() + quantita

                // Aggiorna la quantità del prodotto nel carrello
                prodottoNelCarrelloRef.ref.child("quantity").setValue(nuovaQuantita)

                // Mostra un messaggio di conferma all'utente
                //Toast.makeText(this, "Prodotto aggiunto al carrello! Quantità aggiornata.", Toast.LENGTH_SHORT).show()
            } else {
                // Il prodotto non è presente nel carrello, aggiungilo
                val nuovoProdotto = Article(prodotto.idArticle, prodotto.name, prodotto.image, prodotto.price, quantita, prodotto.type)
                carrelloRef.child("articles/${prodotto.idArticle}").setValue(nuovoProdotto)

                // Mostra un messaggio di conferma all'utente
                //Toast.makeText(this, "Prodotto aggiunto al carrello!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            onClick = { }
                        ) {
                            Text(
                                text = "CATALOGO",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                }
                Divider(color = Color.Black, thickness = 0.8.dp)
            }
        },
        bottomBar = {
            if(showFooter) {
                FooterHome(navController = navController)
            }
            if(openDialog){
                FooterWarning(text = "ARTICOLO AGGIUNTO AL CARRELLO",
                    navController = navController,
                    navigation = "Carrello")
            }

        },
    ) { innerPadding ->
        Column (
                modifier = Modifier
                    .padding(innerPadding)
                ){
            when (val result = mainActivity.viewModel.articleState.value) {
                is DataState.Loading -> {
                    Box() {
                        CircularProgressIndicator()
                    }
                }

                is DataState.Success -> {
                    Row {
                        LazyColumn {
                            items(result.data as List<Article>) { article ->
                                if (article.quantity!!.toFloat() > 0) {
                                    /*CardOrderPhoto(
                                        article = article,
                                        navController = navController,
                                        textProduct = article.name!!,
                                        price = article.price!!.toFloat()
                                    )*/
                                    Column(verticalArrangement = Arrangement.Top) {
                                        Column(modifier = Modifier.padding(top = 3.5.dp)) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp)
                                            ) {
                                               Image(
                                                    painter = painterResource(id = R.drawable.zara_product1),
                                                    contentDescription = "ImmagineProdotto",
                                                    modifier = Modifier
                                                        .border(0.5.dp, Color.Black)
                                                )/*
                                                AsyncImage(
                                                    model ="",
                                                    contentDescription = "ImmagineProdotto",
                                                    modifier = Modifier
                                                        .border(0.5.dp, Color.Black))*/
                                                Column(
                                                    verticalArrangement = Arrangement.Center,
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    modifier = Modifier
                                                        .width(300.dp)
                                                        .height(170.dp)
                                                        .padding(10.dp)
                                                )
                                                {
                                                    Row() {
                                                        Text(
                                                            text = article.name!! + article.quantity + "\n",
                                                            fontSize = 15.sp,
                                                            textAlign = TextAlign.Center
                                                        )
                                                    }
                                                    Row() {
                                                        Text(
                                                            text = article.price!!.toFloat().toString() + " EUR\n", //price
                                                            fontSize = 15.sp,
                                                            textAlign = TextAlign.Center
                                                        )
                                                    }
                                                    Row() {

                                                        Button( colors = ButtonDefaults.buttonColors(Color.Transparent),
                                                            enabled = !(article.quantity == 0),
                                                            onClick = {
                                                                addToCart(article,1)
                                                                val id = article.idArticle!!
                                                                openDialog=true
                                                                /*var trovato = false
                                                                if(cart.filter { it.idArticle == article.idArticle }.size == 1){
                                                                    var newArticle = cart.filter{it.idArticle == article.idArticle}[0]
                                                                    cart.filter { it.idArticle != article.idArticle }
                                                                    newArticle.quantity!!.toInt().plus(1)
                                                                    cart.add(newArticle)
                                                                    val newCart = Cart(userId = userId,articles = cart)
                                                                    mainActivity.viewModel.db
                                                                        .child("Cart")
                                                                        .child(userId)
                                                                        .setValue(newCart)
                                                                } else {
                                                                    val newArticle = article
                                                                    newArticle.quantity = 1
                                                                    cart.add(newArticle)
                                                                    val newCart = Cart(userId = userId,articles = cart)
                                                                    mainActivity.viewModel.db
                                                                        .child("Cart")
                                                                        .child(userId)
                                                                        .setValue(newCart)
                                                                }*/
                                                                /*if(cart!!.any { it.idArticle == id }){
                                                                    var newArticle = cart.filter { it.idArticle!! == id }[0]
                                                                    newArticle.quantity!!.toInt().plus(1)
                                                                    mainActivity.viewModel.db
                                                                        .child("Cart")
                                                                        .child(userId)
                                                                        .child(id.toString())
                                                                        .setValue(newArticle)
                                                                } else {
                                                                    val newArticle = article.copy(quantity = 1)
                                                                    cart.add(newArticle)
                                                                    val newCart = Cart(userId = userId,articles = cart)
                                                                    mainActivity.viewModel.db
                                                                        .child("Cart")
                                                                        .child(userId)
                                                                        .setValue(newCart)
                                                                }
                                                                updateCart()*/
                                                                mainActivity.viewModel.db
                                                                    .child("Article")
                                                                    .child(id.toString())
                                                                    .child("quantity")
                                                                    .setValue(article.quantity!!.toInt() - 1)
                                                            }) {
                                                            Icon(
                                                                Icons.Filled.AddCircle,
                                                                contentDescription = "+",
                                                                tint = Color.DarkGray,
                                                                modifier = Modifier.size(38.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                            }

                                            Row() {
                                                Divider(color = Color.LightGray, thickness = 1.dp)
                                            }

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

    }

        //CardOrderPhoto(navController = navController, textProduct = database.child("value").child("1").child("name").get().toString(), price = 99.95F)

        BackHandler(enabled = true) {
            navController.navigate("Customer")
        }
    }


