package it.polito.progettolocker.dataClass

import android.media.Image
import com.google.firebase.Firebase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.database

@IgnoreExtraProperties
data class Article(
    var name: String? = null,
    var price: Number? = null,
    var quantity: Number? = null,
    var type: String? = null
)

fun addArticle(name: String, price: Number, quantity: Number, type: String) {
    val article = Article(name,price,quantity,type)

    Firebase.database.reference
        .child("Article").setValue(article)
}
