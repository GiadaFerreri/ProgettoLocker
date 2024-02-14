package it.polito.progettolocker.dataClass

import it.polito.progettolocker.dataClass.Article

sealed class DataState {
    class Success(val data:MutableList<*>) : DataState()
    class Failure(val message:String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}