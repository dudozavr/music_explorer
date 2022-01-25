package com.example.music_explorer.data.storage.preferences

import android.content.Context
import android.content.SharedPreferences

object AppPreferencesImpl : AppPreferences {

    private const val PREFERENCES_NAME = "AppPreferences"
    private const val PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED =
        "PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED"
    private const val PREFERENCE_LOIN = "PREFERENCE_LOIN"
    private const val PREFERENCE_PASSWORD = "PREFERENCE_PASSWORD"
    private const val PREFERENCE_TOKEN = "PREFERENCE_TOKEN"

    private var preferences: SharedPreferences? = null

    override fun initPreferences(context: Context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        }
    }

    override fun isSaveCredentialsSelected(): Boolean {
        return preferences?.getBoolean(PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED, false) ?: false
    }

    override fun setSaveCredentialSelected(isSelected: Boolean) {
        preferences?.edit()?.putBoolean(PREFERENCE_IS_SAVE_CREDENTIALS_SELECTED, isSelected)
            ?.apply()
    }

    override fun getLogin(): String {
        return preferences?.getString(PREFERENCE_LOIN, "") ?: ""
    }

    override fun saveLogin(lign: String) {
        preferences?.edit()?.putString(PREFERENCE_LOIN, lign)
            ?.apply()
    }

    override fun getPassword(): String {
        return preferences?.getString(PREFERENCE_PASSWORD, "") ?: ""
    }

    override fun savePassword(password: String) {
        preferences?.edit()?.putString(PREFERENCE_PASSWORD, password)
            ?.apply()
    }

    override fun getToken(): Int {
        return preferences?.getInt(PREFERENCE_TOKEN, 0) ?: 0
    }

    override fun saveToken(token: Int) {
        preferences?.edit()?.putInt(PREFERENCE_TOKEN, token)
            ?.apply()
    }

    override fun checkLogin(login: String) = getLogin() == login

    override fun checkPassword(password: String) = getPassword() == password
}