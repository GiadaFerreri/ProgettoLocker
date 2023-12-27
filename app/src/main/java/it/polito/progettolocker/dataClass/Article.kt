package it.polito.progettolocker.dataClass

import android.media.Image

data class Article (
    val name: String,
    val price: Float,
    var quantity: Int,
    val image: Image
)