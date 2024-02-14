package it.polito.progettolocker.dataClass

import android.net.MacAddress

data class Locker(
    val name: String? = null,
    val address: String? = null,
    val time: String? = null,
    val macaddress: String? = null,
    val position: Map<String,String>? = null,
    var compartments: List<Compartment>? = null
)
