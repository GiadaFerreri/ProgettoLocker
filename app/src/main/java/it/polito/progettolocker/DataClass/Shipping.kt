package it.polito.progettolocker.DataClass

import java.util.Date

enum class States {
    PENDING, HANDLED, DELIVERED, CONCLUDED
}
data class Shipping(
    val shippingId: String,
    val userId: String,
    val deliverymanId : String,
    var state : States,
    val articles : Map<Article, Int>,
    var updates : Map<States, Date>
)
