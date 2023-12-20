package it.polito.progettolocker

import android.content.ContentValues
import android.text.Spannable.Factory
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.database.ValueEventListener
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.Compartment
import it.polito.progettolocker.dataClass.DeliveryMan
import it.polito.progettolocker.dataClass.Locker
import it.polito.progettolocker.dataClass.Shipping
import it.polito.progettolocker.dataClass.States
import it.polito.progettolocker.dataClass.User
import kotlinx.coroutines.tasks.await


class ViewModelLocker(val auth: FirebaseAuth,val databaseReference: DatabaseReference, val eventListener: ValueEventListener) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    val articles : LiveData<List<Article>> = _articles

    //TODO: carrello (da gestire su firebase)
    //tabella che ha come chiave primaria (ID cliente - ID carrello)
    private val _cart = MutableLiveData<Map<Article, Int>> (emptyMap())
    val cart : LiveData<Map<Article, Int>> = _cart

    private val _shippings = MutableLiveData<List<Shipping>>(emptyList())
    val shippings : LiveData<List<Shipping>> = _shippings

    // FUNZIONI PER IL CARRELLO
    fun addToCart (article: Article, quantity : Int){
        var currentCart = _cart.value
        _cart.value = currentCart?.plus(Pair(article, quantity))
        //TODO: aggiungere controllo in activity
    }

    fun removeFromCart (article: Article){
        var currentCart = _cart.value
        _cart.value = currentCart?.filterKeys { it != article }
    }

    fun updateQuantity (article: Article, increase: Boolean){
        var currentCart = _cart.value
        var currentArticleQuantity = currentCart?.get(article)

        if(increase) {
            currentArticleQuantity = currentArticleQuantity!!.plus(1)
        } else {currentArticleQuantity = currentArticleQuantity!!.minus(1)}

        if(currentArticleQuantity == 0){
            removeFromCart(article)
        } else {
        _cart.value = currentCart?.filterKeys { it != article }
        _cart.value = currentCart?.plus(Pair(article, currentArticleQuantity))
        }
    }

    fun purchase() {
        //TODO: collegamento a firebase
    }

    //TODO: locker
    // nome, indirizzo, lista cassetti, stato
    // TODO: cassetto
    // stato (aperto/chiuso)
    // disponibilità (libero/occupato)

    //FUNZIONI LOCKER
    fun findFreeCompartment (locker: Locker){
        //trova il primo vano libero
    }

    // FUNZIONI VANO
    fun openCompartment (compartment: Compartment) {
        if(compartment?.closed == true) compartment.closed = false
    }

    fun closeCompartment(compartment: Compartment){
        if(compartment?.closed == false) compartment.closed = true
    }

    fun reserveCompartment(compartment: Compartment){
        compartment.busy = true
        //TODO: controllo
    }

    fun freeCompartment (compartment: Compartment) {
        compartment.busy = false
        //TODO: controllo
    }

    //TODO: spedizioni
    // ID spedizione, ID cliente, ID fattorino, stato (in corso/ consegnato / conclusa)

    //FUNZIONI SPEDIZIONE
    fun createShipping(article: Article,quantity: Int,userId:String,deliverymanId:String){

    }

        //UPDATES
    fun startShipping(shipping: Shipping,deliverymanId: String){
        if(shipping.deliverymanId == deliverymanId) {
            shipping.state = States.HANDLED
        }
    }

    fun deliveredShipping(shipping: Shipping,deliverymanId: String){
        if(shipping.deliverymanId == deliverymanId) {
            shipping.state = States.DELIVERED
        }
    }

    fun endShipping(shipping: Shipping,userId: String){
        if(shipping.userId == userId) {
            shipping.state = States.CONCLUDED
        }
    }


    //TODO: utente
    // ID utente (firebase), ruolo, lista spedizioni (storico),

    //TODO: fattorino
    // ID fattorino, ruolo, lista spedizioni, orario inizio (presa in carico), orario fine (consegna)


    //FUNZIONI FIREBASE (PROVE)
    fun WriteInDatabase() {
        databaseReference.child("Stringa").setValue("ciao")
    }

    fun SetBoolTrueDB() {
        databaseReference.child("Bool").setValue(true)
    }

    fun SetBoolFalseDB(){
        databaseReference.child("Bool").setValue(false)
    }

    fun WriteIntInDatabase() {
        databaseReference.child("Int").setValue(1)
    }

    @Composable
    fun ReadFromDatabase(databaseReference: DatabaseReference) {
        // Read from the database

        databaseReference.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(ContentValues.TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }

        })
    }

    private suspend fun signInAnonymously(auth: FirebaseAuth): Boolean {
        return try {
            auth.signInAnonymously().await()
            true
        } catch (e: Exception) {
            // Handle sign-in failure
            false
        }
    }

    //FIREBASE
    fun database_writeUser(){

    }

    fun database_writeCart(){

    }

    fun database_readCart(){

    }


}

class ViewModelLockerFactory (val auth: FirebaseAuth, val databaseReference: DatabaseReference,val eventListener: ValueEventListener) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelLocker:: class.java)){
            return ViewModelLocker(auth,databaseReference,eventListener) as T
        }
        else throw IllegalArgumentException("Unknown Class Name")
    }
}