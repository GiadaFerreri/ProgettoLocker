package it.polito.progettolocker.views.customer

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
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.R
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.graphic.Buttons
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome
import it.polito.progettolocker.graphic.FooterWarning
import it.polito.progettolocker.graphic.HeaderDouble
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController) {
    var showFooter by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }

    // Coroutine per far scomparire il popup dopo qualche secondo
    LaunchedEffect(openDialog) {
        if (openDialog) {
            delay(3000) // 3000 millisecondi (3 secondi)
            showFooter = false
            openDialog = false
        }
    }

    /*val articleList = mutableListOf<Article>()
    val database = Firebase.database.reference.child("Articles").child("value")

    fun fetchDataFromFirebase() {
        // Read from the database
        val tempList = mutableListOf<Article>()
        val vm = mainActivity.viewModel
        vm.response.value = DataState.Loading

        FirebaseDatabase.getInstance().getReference("Articles")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(DataSnap in snapshot.children){
                        val article = DataSnap.getValue(Article::class.java)
                        if(article != null){
                            tempList.add(article)
                        }
                    }
                    vm.response.value = DataState.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                vm.response.value = DataState.Failure(error.message)
            }

        })
    }

    database.addChildEventListener(object : ChildEventListener {
        override fun onChildAdded(
            snapshot: DataSnapshot, @Nullable previousChildName: String?
        ) {
            // this method is called when new child is added to
            // our data base and after adding new child
            // we are adding that item inside our  list
            articleList.add(snapshot.getValue(String::class.java)!!)
        }

        override fun onChildChanged(
            snapshot: DataSnapshot, @Nullable previousChildName: String?
        ) {
            // this method is called when the new child is added.
            // when the new child is added to our list we will be called
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            // below method is called when we remove a child from our database.
            // inside this method we are removing the child from our array list
            // by comparing with it's value.
            // after removing the data we are notifying our adapter that the
            // data has been changed.
        }

        override fun onChildMoved(
            snapshot: DataSnapshot, @Nullable previousChildName: String?
        ) {
            // this method is called when we move our
            // child in our database.
            // in our code we are not moving any child.
        }

        override fun onCancelled(error: DatabaseError) {
            // this method is called when we get any
            // error from Firebase with error.
        }
    })*/

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
                                                                openDialog=true
                                                                //TODO("Decrementa la quantità dal catalogo")
                                                                //TODO("Aggiungi al carrello")
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


