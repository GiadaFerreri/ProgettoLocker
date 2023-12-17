package it.polito.progettolocker.dataClass

data class Locker(
    val name: String,
    val location: String,
    var compartments: List<Compartment>,
    var state: Boolean
)
