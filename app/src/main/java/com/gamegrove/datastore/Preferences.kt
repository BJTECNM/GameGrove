package com.gamegrove.datastore

import android.content.Context

class Preferences(val context: Context) {
    val SHARED_PREFERENCES = "Preferences"
    val SHARED_EMAIL = "UserEmail"
    val storage = context.getSharedPreferences(SHARED_PREFERENCES, 0)

    fun saveCredential(email: String) {
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getCredential(): String {
        return storage.getString(SHARED_EMAIL, "")!!
    }
}