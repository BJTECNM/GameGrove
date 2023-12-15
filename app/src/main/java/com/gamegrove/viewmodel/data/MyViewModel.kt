package com.gamegrove.viewmodel.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    // Firebase
    private val auth: FirebaseAuth = Firebase.auth

    // Variables empleadas por la aplicación
    //private val _user = MutableLiveData<String>()
    //val user: LiveData<String> = _user

    // Funciones para modificar los valores de las variables


    // Funciones Firebase
    fun signInWithGoogle(credential: AuthCredential, login: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("login", "Login exitoso")
                        login()
                    }
                }
                .addOnFailureListener {
                    Log.d("login", "Login fallido")
                }
        } catch (ex: Exception) {
            Log.d(
                "login", "Error al logear: " +
                        ex.localizedMessage
            )
        }
    }

    fun signOut(){
        auth.signOut()
        Log.d(
            "login", "signOut"
        )
    }
}