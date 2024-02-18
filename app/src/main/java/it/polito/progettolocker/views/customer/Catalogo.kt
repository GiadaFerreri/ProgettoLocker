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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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

    val userId = mainActivity.userId

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
    val tempList = mutableListOf<Article>()

    val camicia = mainActivity.viewModel.db.child("Article/camicia")


    /*mainActivity.viewModel.db.child("Article/camicia").get().addOnSuccessListener {
        val price = it.child("price")
        val priceValue = price.value
        val quantity = it.child("quantity")
        val quantityValue = quantity.value
    }*/

    mainActivity.viewModel.db.child("Article")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                for(snapshot in snapshot.children){
                    val article = snapshot.getValue(Article::class.java)
                    tempList.add(article!!)
                }
                mainActivity.viewModel.articleState.value = DataState.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })





    fun writeCatalog() {
        val a0 = Article("gonna","https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Gonna%20pantalone%20a%20pieghe.png?alt=media&token=51da885a-7342-44b1-a59f-4e5fc89feb11","Gonna pantalone a pieghe",29.95,5, "small")
        val a1 = Article("camicia","https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Camicia%20Oxford%20a%20righe%20oversize.png?alt=media&token=c3fee7f1-271b-4508-bff3-ec192ef45a04","Camicia Oxford a righe oversize",32.95,5, "small")
        val a2 = Article("jeans","https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Jeans%20Z1975%20dritti%20a%20vita%20bassa.png?alt=media&token=f8a174ef-4f83-41e3-a7da-b691dc1aa847","Jeans Z1975 dritti a vita bassa",39.95,5, "small")
        val a3 = Article("pullover", "https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Pullover%20struttura%20punto%20intrecciato.png?alt=media&token=bca04635-6072-4749-8413-a86926d192c5","Pullover struttura punto intrecciato",49.95,5,"small")
        val a4 = Article("parka","https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Parka%20lungo.png?alt=media&token=4887a947-71af-468b-bda2-0f2f5f0c6f91","Parka lungo",79.95,5, "big")
        val a5 = Article("stivali","https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Stivali%20in%20vernice%20con%20il%20tacco.png?alt=media&token=ba56322f-1e92-4c44-a6b7-d4160c6f44db","Stivali in vernice con il tacco",79.95,5, "small")

        mainActivity.viewModel.db.child("Article").child("gonna").setValue(a0)
        mainActivity.viewModel.db.child("Article").child("camicia").setValue(a1)
        mainActivity.viewModel.db.child("Article").child("jeans").setValue(a2)
        mainActivity.viewModel.db.child("Article").child("pullover").setValue(a3)
        mainActivity.viewModel.db.child("Article").child("parka").setValue(a4)
        mainActivity.viewModel.db.child("Article").child("stivali").setValue(a5)
    }
    //writeCatalog()

    /*fun updateCart(){
        mainActivity.viewModel.db.child("Cart").child(userId).child("articles")
            .addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val tempList = snapshot.getValue<ArrayList<Map<String,Any>>>() as ArrayList<Map<String,Any>>

                    tempList.forEach {
                        cart.add(Article(idArticle = it["idArticle"] as String, quantity = it["quantity"] as Number, price = it["price"] as Number, name = it["name"] as String, type = it["type"] as String))
                    }
                    cartState.value = DataState.Success(cartList)
                    *//*val updatedCart = cartList.filter { it.userId == userId }[0].articles as List<Article>
                    cart.clear()
                    updatedCart.forEach{
                        cart.add(it)
                    }*//*

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                }
            })
    }*/

    fun addToCart(prodotto: Article, quantita: Long) {
        // Ottieni il carrello dell'utente dal database
        val carrelloRef = mainActivity.viewModel.db.child("Cart/${userId}")

        // Controlla se il prodotto è già presente nel carrello
        val prodottoNelCarrelloRef = carrelloRef.child("articles/${prodotto.idArticle}")
        prodottoNelCarrelloRef.get().addOnSuccessListener {
            if (it.exists()) {
                // Il prodotto è già presente nel carrello, aggiorna la quantità
                val quantitaAttuale = it.child("quantity")
                val quantitaAttualeValue = quantitaAttuale.value
                val nuovaQuantita = quantitaAttualeValue as Long + quantita

                // Aggiorna la quantità del prodotto nel carrello
                prodottoNelCarrelloRef.ref.child("quantity").setValue(nuovaQuantita)

                // Mostra un messaggio di conferma all'utente
                //Toast.makeText(this, "Prodotto aggiunto al carrello! Quantità aggiornata.", Toast.LENGTH_SHORT).show()
            } else {
                // Il prodotto non è presente nel carrello, aggiungilo
                val nuovoProdotto = Article(idArticle = prodotto.idArticle, name = prodotto.name, image = prodotto.image, price = prodotto.price, quantity = quantita, type = prodotto.type)
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

                                                AsyncImage(
                                                    model = article.image!!,
                                                    contentDescription = "Immagine prodotto",
                                                    modifier = Modifier
                                                        .border(0.5.dp, Color.Black)
                                                )

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
                                                            text = article.name!!+ "\n",
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
                                                            enabled = !(article.quantity == 0L),
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


