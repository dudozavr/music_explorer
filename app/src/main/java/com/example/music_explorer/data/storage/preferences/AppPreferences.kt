package com.example.music_explorer.data.storage.preferences

import android.content.Context

interface AppPreferences {
    fun isSaveCredentialsSelected(): Boolean
    fun setSaveCredentialSelected(isSelected: Boolean)

    fun getPassword(): String
    fun savePassword(password: String)

    fun getLogin(): String
    fun saveLogin(lign: String)

    fun getToken(): Int
    fun saveToken(token: Int)

    fun checkLogin(login: String): Boolean
    fun checkPassword(password: String): Boolean

    fun initPreferences(context: Context)
}