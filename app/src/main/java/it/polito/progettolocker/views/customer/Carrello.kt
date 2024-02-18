package it.polito.progettolocker.views.customer

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.graphic.CardProductCard
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterDoubleBlack
import it.polito.progettolocker.graphic.HeaderX

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carrello(mainActivity: MainActivity, navController: NavController){

    val cartList = mutableListOf<Article>()
    val cartState = mainActivity.viewModel.cartState
    val userId = mainActivity.userId

    var price = remember { mutableDoubleStateOf(cartList.sumOf { it.price!! * it.quantity!! }) }

    //TODO("Controllare che il carrello esista altrimenti crearlo")

    mainActivity.viewModel.db.child("Cart").child(userId).child("articles")
        .addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                cartList.clear()
                for(snapshot in snapshot.children){
                    val article = snapshot.getValue(Article::class.java)
                    cartList.add(article!!)
                }
                cartState.value = DataState.Success(cartList)
                price.value = cartList.sumOf { it.price!! * it.quantity!! }
            }

            /*override fun onDataChange2(snapshot: DataSnapshot) {
               cartList.clear()

                val tempList = snapshot.getValue<ArrayList<Map<String,Any>>>() as ArrayList<Map<String,Any>>

                tempList.forEach {
                    if(it != null) cartList.add(Article(idArticle = it["idArticle"] as Number, image = it["image"] as String, quantity = it["quantity"] as Number, price = it["price"] as Number, name = it["name"] as String, type = it["type"] as String))
                }
                cartState.value = DataState.Success(cartList)
            }*/

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })


    Scaffold(
        containerColor = Color.White,
        topBar = {
            HeaderX(text = "CARRELLO", navController = navController, onClickDestination = "Customer")
        },
        bottomBar = {
            FooterDoubleBlack(price = price.value, navController = navController)
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){
            when (val result = mainActivity.viewModel.cartState.value) {
                is DataState.Loading -> {
                    Box() {
                        CircularProgressIndicator()
                    }
                }

                is DataState.Success -> {
                    Row {
                        LazyColumn{
                            items(result.data as List<Article>) { article ->
                                    CardProductCard(
                                        mainActivity = mainActivity,
                                        navController = navController,
                                        article = article,
                                        quantity = article.quantity!!.toInt()
                                    )
                                    Divider(color = Color.LightGray, thickness = 1.dp)
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
        navController.navigate("Customer")
    }
}