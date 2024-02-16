package it.polito.progettolocker.dataClass

import android.media.Image
import com.google.firebase.Firebase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.database

@IgnoreExtraProperties
data class Article(
    var idArticle: Number? = null,
    var name: String? = null,
    var price: Number? = null,
    var quantity: Number? = null,
    var type: String? = null
)
