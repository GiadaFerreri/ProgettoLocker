package it.polito.progettolocker.dataClass

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Article(
    var idArticle: String? = null,
    var image: String? = null,
    var name: String? = null,
    var price: Double? = null,
    var quantity: Long? = null,
    var type: String? = null
)
