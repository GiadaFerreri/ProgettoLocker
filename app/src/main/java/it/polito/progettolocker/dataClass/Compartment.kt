package it.polito.progettolocker.dataClass

data class Compartment(
    var idvano: Int? = null,
    var chiuso : Boolean? = null,
    var inuso : Boolean? = null,
    var type : String? = null
)
