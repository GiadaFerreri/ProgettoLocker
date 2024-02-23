package it.polito.progettolocker.dataClass

enum class UserType {
    CUSTOMER, DELIVERY
}
data class User(
    val uid : String? = null,
    val mail: String? = null,
    val type: UserType? = null
)
