package it.polito.progettolocker.dataClass

data class DeliveryMan(
    val idDeliveryMan: String,
    var assignedShippings : List<Shipping>
)
