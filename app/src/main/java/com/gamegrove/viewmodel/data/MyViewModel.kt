package com.gamegrove.viewmodel.data

import android.util.Log
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
    private val db = FirebaseFirestore.getInstance()
    private val uid = Firebase.auth.currentUser!!.uid

    // Variables empleadas por la aplicación
    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _searchResult = MutableLiveData<List<Game>>()
    val searchResult: LiveData<List<Game>> = _searchResult

    private val _favoritesList = MutableLiveData<List<Game>>()
    val favoritesList: LiveData<List<Game>> = _favoritesList

    // Funciones para modificar los valores de las variables
    fun changeSearch(search: String) {
        _search.value = search
    }

    fun changeSearchResult(listResult: List<Game>) {
        _searchResult.value = listResult
    }

    fun getFavoriteList(){
        db.collection(uid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val aux = Games.items.filter { it.title.contains(document.id, true) }
                    _favoritesList.value = _favoritesList.value?.plus(aux) ?: listOf()
                    val newList = mutableListOf<Game>()
                    val aux2 = _favoritesList.value?.toHashSet()
                    aux2?.forEach { newList.add(it) }
                    _favoritesList.value = newList
                }
            }
            .addOnFailureListener {

            }
    }

    // Funciones Firebase
    fun signInWithGoogle(credential: AuthCredential, login: () -> Unit) = viewModelScope.launch {
        try {
            _auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("login", "Login exitoso")
                        login()
                    }
                }
                .addOnFailureListener {
                    Log.d("login", "Login fallido")
                }
        } catch (e: Exception) {
            Log.d(
                "login", "Error al logear: " +
                        e.localizedMessage
            )
        }
    }

    fun signOut() {
        _auth.signOut()
    }

    fun addFavoriteItem(game: Game) {
        try {
            val remindHashMap = hashMapOf(
                "title" to game.title,
                "launch" to game.launch,
                "plataform" to game.plataform,
                "description" to game.description
            )
            db.collection(uid).document(game.title).set(remindHashMap)
                .addOnCompleteListener {

                }
                .addOnFailureListener {

                }
        } catch (e: Exception) {
            Log.d(
                "login", "Error al guardar favorito: " +
                        e.localizedMessage
            )
        }
    }

    fun deleteFavoriteItem(game: Game) {
        try {
            db.collection(uid).document(game.title).delete()
                .addOnCompleteListener {

                }
                .addOnFailureListener {

                }
        } catch (e: Exception) {
            Log.d(
                "login", "Error al quitar favorito: " +
                        e.localizedMessage
            )
        }
    }
}