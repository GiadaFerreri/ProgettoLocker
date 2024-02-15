package it.polito.progettolocker.views.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.DataState
import it.polito.progettolocker.graphic.CardOrderPhoto
import it.polito.progettolocker.graphic.CardWarning
import it.polito.progettolocker.graphic.FooterHome

@Composable
fun Catalogo(mainActivity: MainActivity, navController: NavController) {

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


    Column(verticalArrangement = Arrangement.Top) {
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

    when (val result = mainActivity.viewModel.articleState.value) {
        is DataState.Loading -> {
            Box() {
                CircularProgressIndicator()
            }
        }

        is DataState.Success -> {
            LazyColumn {
                items(result.data as List<Article>) { article ->
                    if(article.quantity!!.toFloat() > 0){
                        CardOrderPhoto(
                            article = article,
                            navController = navController,
                            textProduct = article.name!!,
                            price = article.price!!.toFloat()
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


    //CardOrderPhoto(navController = navController, textProduct = database.child("value").child("1").child("name").get().toString(), price = 99.95F)
    FooterHome(navController = navController)

    BackHandler(enabled = true) {
        navController.navigate("Customer")
    }
}

