package it.polito.progettolocker.graphic

import android.content.ContentValues
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.MainActivity
import it.polito.progettolocker.dataClass.Article
import kotlin.math.roundToInt

//Card pagina Carrello
enum class DragAnchors {
    Start,
    Center,
    End,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableItem(
    state: AnchoredDraggableState<DragAnchors>,
    content: @Composable BoxScope.() -> Unit,
    startAction: @Composable (BoxScope.() -> Unit)? = {},
    endAction: @Composable (BoxScope.() -> Unit)? = {}
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color.White,

                )
    ) {

        endAction?.let {
            endAction()
        }

        startAction?.let {
            startAction()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .offset {
                    IntOffset(
                        x = -state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal, reverseDirection = true),
            content = content
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable


fun CardProductCard(mainActivity: MainActivity, navController: NavController, article: Article, textProduct:String, price: Float, quantity: Int, image:String) {
    val userId = mainActivity.user.uid
    val density = LocalDensity.current
    val defaultActionSize = 80.dp
    val endActionSizePx = with(density) { (defaultActionSize ).toPx() }
    val startActionSizePx = with(density) { (defaultActionSize*2).toPx() }
    //val (quantityRequired, setquanityRequired) = remember { mutableIntStateOf(1) }
    var catalogueQuantity = 0L

    val catalogueRef = mainActivity.viewModel.db.child("Article/${article.idArticle}")

    catalogueRef.child("quantity").get().addOnSuccessListener {
        catalogueQuantity = it.value as Long
    }

    catalogueRef.child("quantity").addValueEventListener(object: ValueEventListener {

        override fun onDataChange(snapshot: DataSnapshot) {

            for(snapshot in snapshot.children){
                catalogueQuantity = snapshot.getValue(Long::class.java)!!
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
        }
    })

    var state = remember(article.idArticle) {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            anchors = DraggableAnchors {
                //DragAnchors.Start at -startActionSizePx
                DragAnchors.Center at 0f
                DragAnchors.End at endActionSizePx
            },
            positionalThreshold = { distance: Float -> distance * 0.1f },
            velocityThreshold = { with(density) { 70.dp.toPx() } },
            animationSpec = tween(),
        )
    }

    fun updateCart(article: Article, delete: Boolean, increase: Boolean) {
        val cartRef = mainActivity.viewModel.db.child("Cart/${userId}")
        val articleRef = cartRef.child("articles/${article.idArticle}")
        var oldQuantity = article.quantity!!
        var newQuantity = oldQuantity
        var newCatalogQuantity = catalogueQuantity

        catalogueRef.child("quantity").get().addOnSuccessListener {
            catalogueQuantity = it.value as Long
        }

        if(delete) {
            newQuantity = 0
            newCatalogQuantity = catalogueQuantity + oldQuantity
        } else {
            if(increase){
                if(catalogueQuantity > 0){
                    newQuantity = article.quantity!! + 1L
                    newCatalogQuantity = catalogueQuantity - 1L
                }
            } else {
                if(oldQuantity > 0){
                    newQuantity = article.quantity!! - 1L
                    newCatalogQuantity = catalogueQuantity + 1L
                }
            }
        }

        articleRef.get().addOnSuccessListener {

            if(newQuantity == 0L){
                articleRef.removeValue()
                catalogueRef.ref.child("quantity").setValue(newCatalogQuantity)
            } else
            {
                articleRef.ref.child("quantity").setValue(newQuantity)
                catalogueRef.ref.child("quantity").setValue(newCatalogQuantity)
            }

        }
    }

    Column(verticalArrangement = Arrangement.Top) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .fillMaxHeight()

        ) {
            DraggableItem(state = state, content = {

                    TextButton(
                        shape= RectangleShape,
                        onClick = {
                            updateCart(article = article, delete = true, increase = false)
                                  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        modifier =
                        Modifier
                            .fillMaxHeight()
                            .width(100.dp)
                            .padding(0.dp)
                            .offset {
                                IntOffset(
                                    ((-state
                                        .requireOffset() + 5.8 * endActionSizePx))
                                        .roundToInt(), 0
                                )
                            }

                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Elimina"
                        )
                    }

                    Column() {
                        AsyncImage(
                            model = image,
                            contentDescription = "Immagine prodotto",
                            modifier = Modifier
                                .border(0.5.dp, Color.Black)
                        )

                    }
                    Column(
                        modifier = Modifier
                            .width(800.dp)
                            .height(200.dp)
                            .padding(start = 150.dp)
                    ) {


                        Row(
                            horizontalArrangement = Arrangement.End, modifier = Modifier
                                .width(800.dp)
                                .height(60.dp)
                                .padding(0.dp)
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                onClick = {
                                    updateCart(article = article, delete = true, increase = false)
                                          },
                                modifier = Modifier.padding(0.dp)
                            ) {
                                Icon(
                                    Icons.Outlined.Close,
                                    contentDescription = "Chiudi",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(0.dp)
                                        .size(20.dp)
                                )
                            }

                        }
                        Column(
                            modifier = Modifier
                                .width(800.dp)
                                .height(250.dp)
                                .padding(start = 8.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        )
                        {


                            Row() {
                                Text(
                                    text = article.name!!,
                                    fontSize = 15.sp
//                        style = TextStyle(
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight(400),
//                            color = Color(0xFF000000),
//                            textAlign = TextAlign.Left
//                        )

                                )
                            }
                            Row() {

                                Text(
                                    text = "${article.price} EUR\n", //price
                                    fontSize = 15.sp
//                        style = TextStyle(
//                            fontSize = 15.sp,
//                            fontWeight = FontWeight(400),
//                            color = Color(0xFF000000),
//                            textAlign = TextAlign.Left
//                        )

                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .border(width = 0.5.dp, color = Color(0xFF000000))
                                    .width(200.dp)
                                    .height(30.dp)
                            )
                            {
                                Button(
                                    onClick = {
                                        updateCart(article = article, delete = false, increase = false)
                                    },
                                    shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    modifier = Modifier.weight(1.5f)
                                ) {
                                    Text(
                                        text = "-",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center
                                        )

                                    )

                                }

                                Divider(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp), color = Color.Black
                                )
                                Button(
                                    onClick = {  },
                                    shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    modifier = Modifier.weight(1.5f)
                                ) {
                                    Text(
                                        text = article.quantity.toString(),
                                        modifier= Modifier.padding(0.dp),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp), color = Color.Black
                                )

                                Button(
                                    onClick = {
                                        updateCart(article = article, delete = false, increase = true)
                                    },
                                    shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    modifier = Modifier.weight(1.5f)
                                ) {
                                    Text(
                                        text = "+",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center
                                        )

                                    )
                                }

                            }
                        }

                    }


            }


            )

        }
    }
}







