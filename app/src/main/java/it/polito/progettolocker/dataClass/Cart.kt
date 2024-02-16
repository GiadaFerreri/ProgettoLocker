package it.polito.progettolocker.dataClass

data class Cart(
    val userId: String? = null,
    val articles: List<Article>? = null
)