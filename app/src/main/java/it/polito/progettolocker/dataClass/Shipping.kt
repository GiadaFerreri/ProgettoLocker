package it.polito.progettolocker.dataClass

import java.util.Date

enum class States {
    PENDING, HANDLED, DELIVERED, CONCLUDED
}
data class Shipping(
    val shippingId: String? = null,
    val userId: String?= null,
    val deliverymanId : String?= null,
    var state : States,
    val articles: List<Article>? = null,
    var updates : Map<States, Date>
)
