package it.polito.progettolocker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.polito.progettolocker.dataClass.Article

class ViewModelLocker : ViewModel() {

    //TODO: collegamento a Firebase

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    val articles : LiveData<List<Article>> = _articles

    //TODO: carrello (da gestire su firebase)
    //tabella che ha come chiave primaria (ID cliente - ID carrello)
    private val _cart = MutableLiveData<Map<Article, Int>> (emptyMap())
    val cart : LiveData<Map<Article, Int>> = _cart

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

    //TODO: spedizioni
    // ID spedizione, ID cliente, ID fattorino, stato (in corso/ consegnato / conclusa)

    //TODO: utente
    // ID utente (firebase), ruolo, lista spedizioni (storico),

    //TODO: fattorino
    // ID fattorino, ruolo, lista spedizioni, orario inizio (presa in carico), orario fine (consegna)
}