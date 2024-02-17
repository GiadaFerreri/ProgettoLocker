package it.polito.progettolocker.dataClass

enum class States {
    PENDING, HANDLED, DELIVERED, CONCLUDED
}
data class Shipping(
    val shippingId: String? = null,
    val userId: String?= null,
    val deliverymanId : String?= null,
    var state : States,
    val articles: List<Article>? = null,
    val depositId: String? = null,
    val pickupId: String? = null,
    val lockerId: String? = null,
    val compartmentId: String? = null
    //var updates : Map<States, Date>
)
