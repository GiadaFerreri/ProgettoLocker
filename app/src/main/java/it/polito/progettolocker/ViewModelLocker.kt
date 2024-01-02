package it.polito.progettolocker

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
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


class ViewModelLocker(val auth: FirebaseAuth,val databaseReference: DatabaseReference) : ViewModel() {

    private val usersReference = databaseReference.child("Users")
    private val deliverymenReference = databaseReference.child("Delivery Men")
    private val catalogueReference = databaseReference.child("Articles")
    private val shippingReference = databaseReference.child("Shippings")

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    var articles : LiveData<List<Article>> = _articles

    //TODO: carrello (da gestire su firebase)
    //tabella che ha come chiave primaria (ID cliente - ID carrello)
    private val _cart = MutableLiveData<Map<Article, Int>> (emptyMap())
    var cart : LiveData<Map<Article, Int>> = _cart

    private val _shippings = MutableLiveData<List<Shipping>>(emptyList())
    var shippings : LiveData<List<Shipping>> = _shippings

    private val _catalogue = MutableLiveData(listOf(
        Article("Gonna pantalone a pieghe",29.95,5),
        Article("Camicia Oxford a righe oversize",32.95,5),
        Article("Jeans Z1975 dritti a vita bassa",39.95,5),
        Article("Pullover struttura punto intrecciato",49.95,5),
        Article("Parka lungo",79.95,5),
        Article("Stivali in vernice con il tacco",79.95,5)
    ))
    var catalogue : LiveData<List<Article>> = _catalogue

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

    //FIREBASE
    fun database_writeUser(user: User){
        usersReference.child("${user.userId}").setValue(user)
    }

    fun database_readUser(userId: String){

    }

    fun database_writeDeliveryman(deliveryMan: DeliveryMan){
        deliverymenReference.child("${deliveryMan.idDeliveryMan}").setValue(deliveryMan)
    }

    fun database_readDeliveryman(){

    }

    fun database_writeCart(userId: String){
        //TODO: come gestire il carrello?
    }

    fun database_addArticletoCart(article: Article){

    }

    fun database_removeArticlefromCart(article: Article){

    }

    fun database_cartArticleQuantity(article: Article){
        //modifica la quantità di un dato articolo nel carrello
    }

    fun database_readCart(){

    }

    fun database_writeCatalogue(){
        catalogueReference.setValue(catalogue)
    }

    fun database_updateArticleQuantity(article: Article){
        //modifica la quantità generale dell'articolo
        
    }

    fun database_readCatalogue(){
        //TODO: leggere lista articoli
    }

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

    fun switchBoolean() {
        val documentReference = databaseReference.child("Bool")

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Il documento esiste, leggere il valore booleano
                    val currentValue = document.getValue(Boolean::class.java) ?: false

                    // Aggiornare il valore booleano in base al tuo requisito
                    val updatedValue = !currentValue

                    // Aggiornare il valore booleano nel documento
                    documentReference.setValue(updatedValue)

                } else {
                    Log.d(ContentValues.TAG, "Il documento non esiste.")
                }
            }.addOnFailureListener { e ->
                Log.e(ContentValues.TAG, "failed to read value", e)
            }

    }

    fun incrementInt() {
        val documentReference = databaseReference.child("Int")

        documentReference.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Il documento esiste, leggere il valore booleano
                    val currentValue = document.getValue(Int::class.java)

                    // Aggiornare il valore booleano in base al tuo requisito
                    val updatedValue = currentValue?.plus(1)

                    // Aggiornare il valore booleano nel documento
                    documentReference.setValue(updatedValue)

                } else {
                    Log.d(ContentValues.TAG, "Il documento non esiste.")
                }
            }.addOnFailureListener { e ->
                Log.e(ContentValues.TAG, "failed to read value", e)
            }

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


}

class ViewModelLockerFactory (val auth: FirebaseAuth, val databaseReference: DatabaseReference) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelLocker:: class.java)){
            return ViewModelLocker(auth,databaseReference) as T
        }
        else throw IllegalArgumentException("Unknown Class Name")
    }
}