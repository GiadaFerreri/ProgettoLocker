package it.polito.progettolocker.DataClass

import android.media.Image

data class Article (
    val name: String,
    val price: Int,
    var quantity: Int,
    val image: Image
)