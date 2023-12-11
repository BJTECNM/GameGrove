package com.gamegrove.viewmodel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Model : ViewModel() {
    // Variables empleadas por la aplicación
    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _passwordConfirm = MutableLiveData<String>()
    val passwordConfirm: LiveData<String> = _passwordConfirm

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _firstSurname = MutableLiveData<String>()
    val firstSurname: LiveData<String> = _firstSurname

    private val _secondSurname = MutableLiveData<String>()
    val secondSurname: LiveData<String> = _secondSurname

    private val _code = MutableLiveData<String>()
    val code: LiveData<String> = _code

    // Funciones para modificar los valores de las variables
    fun ResetValues() {
        _user.value = ""
        _password.value = ""
        _passwordConfirm.value = ""
        _name.value = ""
        _firstSurname.value = ""
        _secondSurname.value = ""
    }

    fun ModifyUser(valor: String) {
        _user.value = valor
    }

    fun ModifyPassword(valor: String) {
        _password.value = valor
    }

    fun ModifyPasswordConfirm(valor: String) {
        _passwordConfirm.value = valor
    }

    fun ModifyName(valor: String) {
        _name.value = valor
    }

    fun ModifyFirstSurname(valor: String) {
        _firstSurname.value = valor
    }

    fun ModifySecondSurname(valor: String) {
        _secondSurname.value = valor
    }
}