package it.polito.progettolocker

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import it.polito.progettolocker.dataClass.Article
import it.polito.progettolocker.dataClass.DataState
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
    private val cartReference = databaseReference.child("Cart")

    val articleState: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val lockerState: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val cartState: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val shippingState: MutableState<DataState> = mutableStateOf(DataState.Empty)

    val db = Firebase.database.reference

    val vanoDaUsare = mutableIntStateOf(0)

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    var articles : LiveData<List<Article>> = _articles

    //TODO: carrello (da gestire su firebase)
    //tabella che ha come chiave primaria (ID cliente - ID carrello)
    private val _cart = MutableLiveData<Map<Article, Int>> (emptyMap())
    var cart : LiveData<Map<Article, Int>> = _cart

    private val _shippings = MutableLiveData<List<Shipping>>(emptyList())
    var shippings : LiveData<List<Shipping>> = _shippings

    private val _catalogue = MutableLiveData(listOf<Article>(
        /*Article(0,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Gonna%20pantalone%20a%20pieghe.png?alt=media&token=51da885a-7342-44b1-a59f-4e5fc89feb11","Gonna pantalone a pieghe",29.95,5, "small"),
        Article(1,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Camicia%20Oxford%20a%20righe%20oversize.png?alt=media&token=c3fee7f1-271b-4508-bff3-ec192ef45a04","Camicia Oxford a righe oversize",32.95,5, "small"),
        Article(2,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Jeans%20Z1975%20dritti%20a%20vita%20bassa.png?alt=media&token=f8a174ef-4f83-41e3-a7da-b691dc1aa847","Jeans Z1975 dritti a vita bassa",39.95,5, "small"),
        Article(3, "https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Pullover%20struttura%20punto%20intrecciato.png?alt=media&token=bca04635-6072-4749-8413-a86926d192c5","Pullover struttura punto intrecciato",49.95,5,"small"),
        Article(4,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Parka%20lungo.png?alt=media&token=4887a947-71af-468b-bda2-0f2f5f0c6f91","Parka lungo",79.95,5, "big"),
        Article(5,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Stivali%20in%20vernice%20con%20il%20tacco.png?alt=media&token=ba56322f-1e92-4c44-a6b7-d4160c6f44db","Stivali in vernice con il tacco",79.95,5, "small")
    */))
    var catalogue : LiveData<List<Article>> = _catalogue

    fun writeCatalogue(){
        val articleList = mutableListOf<Article>(/*
            Article(0,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Gonna%20pantalone%20a%20pieghe.png?alt=media&token=51da885a-7342-44b1-a59f-4e5fc89feb11","Gonna pantalone a pieghe",29.95,5, "small"),
            Article(1,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Camicia%20Oxford%20a%20righe%20oversize.png?alt=media&token=c3fee7f1-271b-4508-bff3-ec192ef45a04","Camicia Oxford a righe oversize",32.95,5, "small"),
            Article(2,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Jeans%20Z1975%20dritti%20a%20vita%20bassa.png?alt=media&token=f8a174ef-4f83-41e3-a7da-b691dc1aa847","Jeans Z1975 dritti a vita bassa",39.95,5, "small"),
            Article(3,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Pullover%20struttura%20punto%20intrecciato.png?alt=media&token=bca04635-6072-4749-8413-a86926d192c5","Pullover struttura punto intrecciato",49.95,5,"small"),
            Article(4,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Parka%20lungo.png?alt=media&token=4887a947-71af-468b-bda2-0f2f5f0c6f91","Parka lungo",79.95,5, "big"),
            Article(5,"https://firebasestorage.googleapis.com/v0/b/locker-53147.appspot.com/o/Stivali%20in%20vernice%20con%20il%20tacco.png?alt=media&token=ba56322f-1e92-4c44-a6b7-d4160c6f44db","Stivali in vernice con il tacco",79.95,5, "small")
        */)
        var index = 0
        for(article in articleList){
            db.child("Article").child(index.toString()).setValue(article)
            index++
        }

    }

    // FUNZIONI PER IL CARRELLO
    fun addToCart (article: Article, quantity : Int){
        var currentCart = _cart.value
        _cart.value = currentCart?.plus(Pair(article, quantity))
        TODO( "aggiungere controllo in activity")
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

    private val _counter = MutableLiveData<Int>().also { it.value = 0 }
    fun lockerCount(): Int? {
        if(_counter.value == 0) {
            _counter.value = _counter.value?.plus(1)
        }
        if(_counter.value == 1) {
            _counter.value = 0
        }
        return _counter.value
    }

    /*// FUNZIONI VANO
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
    }*/

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