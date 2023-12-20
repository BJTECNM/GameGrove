package com.gamegrove.viewmodel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.data.datagames.Games
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    // Firebase
    private val _auth: FirebaseAuth = Firebase.auth

    // Variables empleadas por la aplicación
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _searchResult = MutableLiveData<List<Game>>()
    val searchResult: LiveData<List<Game>> = _searchResult

    private val _favoritesList = MutableLiveData<List<Game>>()
    val favoritesList: LiveData<List<Game>> = _favoritesList

    private val _gameSelected = MutableLiveData<Game>()
    val gameSelected: LiveData<Game> = _gameSelected

    private val _onFavorites = MutableLiveData<Boolean>()
    val onFavorites: LiveData<Boolean> = _onFavorites

    private val _isSelected = MutableLiveData<Boolean>()
    val isSelected: LiveData<Boolean> = _isSelected

    private val _isPremiere = MutableLiveData<Boolean>()
    val isPremiere: LiveData<Boolean> = _isPremiere

    // Funciones para modificar los valores de las variables
    fun changeSearch(search: String) {
        _search.value = search
    }

    fun changeSearchResult(listResult: List<Game>) {
        _searchResult.value = listResult
    }

    fun restoreErrorState() {
        _error.value = ""
    }

    fun selectGame(game: Game) {
        verifyOnFavorites(game)
        _gameSelected.value = game
        _isSelected.value = true
    }

    fun deselectGame() {
        _isSelected.value = false
        _isPremiere.value = false
    }

    fun selectPremiere() {
        _isPremiere.value = true
    }

    // Funciones Firebase
    fun signInWithGoogle(credential: AuthCredential, login: () -> Unit) = viewModelScope.launch {
        try {
            _auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        login()
                    }
                }
                .addOnFailureListener {
                    _error.value = "Login fallido\nVerifique su conexión a Internet"
                }
        } catch (e: Exception) {
            _error.value = "Login fallido\nVerifique su conexión a Internet"
        }
    }

    fun signOut() {
        _auth.signOut()
    }

    fun addFavoriteItem(db: FirebaseFirestore, uid: String, game: Game) = viewModelScope.launch {
        try {
            val remindHashMap = hashMapOf(
                "title" to game.title,
                "launch" to game.launch,
                "plataform" to game.plataform,
                "genre" to game.genre
            )
            db.collection(uid).document(game.title).set(remindHashMap)
                .addOnCompleteListener {
                    _onFavorites.value = true
                }
                .addOnFailureListener {
                    _error.value = "No se pudo añadir a favoritos"
                }
            _onFavorites.value = true
        } catch (e: Exception) {
            _error.value = "No se pudo añadir a favoritos"
        }
    }

    fun deleteFavoriteItem(db: FirebaseFirestore, uid: String, game: Game) = viewModelScope.launch {
        try {
            db.collection(uid).document(game.title).delete()
                .addOnCompleteListener {
                    _onFavorites.value = false
                }
                .addOnFailureListener {
                    _error.value = "No se pudo quitar de favoritos"
                }
            _onFavorites.value = false
        } catch (e: Exception) {
            _error.value = "No se pudo quitar de favoritos"
        }
    }

    fun getFavoriteList(db: FirebaseFirestore, uid: String) = viewModelScope.launch {
        _favoritesList.value = emptyList()
        try {
            db.collection(uid)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val aux = Games.items.filter { it.title.contains(document.id, true) }
                        _favoritesList.value = _favoritesList.value?.plus(aux) ?: listOf()
                    }
                    val auxList = mutableListOf<Game>()
                    val aux = _favoritesList.value?.toHashSet()
                    aux?.forEach { auxList.add(it) }
                    _favoritesList.value = auxList
                }
                .addOnFailureListener {
                    _error.value = "No se pudo cargar sus favoritos"
                }
        } catch (e: Exception) {
            _error.value = "No se pudo cargar sus favoritos"
        }
    }

    private fun verifyOnFavorites(game: Game) = viewModelScope.launch {
        val aux = favoritesList.value?.filter { it.title.contains(game.title, true) }
        if (aux.isNullOrEmpty()) {
            _onFavorites.value = false
        } else {
            _onFavorites.value = true
        }
    }
}