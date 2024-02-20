package it.polito.progettolocker.dataClass

data class Locker(
    val name: String? = null,
    val address: String? = null,
    val time: String? = null,
    val macaddress: String? = null,
    val position: Map<String,String>? = null,
    var compartments: List<Compartment>? = null,
    var pickupId: String? = null
)
