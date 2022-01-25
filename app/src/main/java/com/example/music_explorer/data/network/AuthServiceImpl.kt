package com.example.music_explorer.data.network

import android.util.Patterns
import com.example.music_explorer.data.storage.preferences.AppPreferences
import com.example.music_explorer.data.storage.preferences.AppPreferencesImpl
import kotlin.random.Random

class AuthServiceImpl : AuthService {

    private val preferences: AppPreferences = AppPreferencesImpl

    override fun isSignUpValid(
        name: String,
        email: String,
        password: String,
        conformation: String
    ) = isUserNameCorrect(name = name)
            && isEmailCorrect(email = email)
            && isPasswordCorrect(password = password)
            && isPasswordConformationCorrect(password = password, conformation = conformation)

    override fun isSignInValid(email: String, password: String) = isEmailCorrect(email = email)
            && isPasswordCorrect(password = password)
            && preferences.checkLogin(login = email)
            && preferences.checkPassword(password = password)

    override fun getToken(): Int {
        return Random.nextInt()
    }

    private fun isUserNameCorrect(name: String) = name.isNotBlank()

    private fun isEmailCorrect(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordCorrect(password: String): Boolean {
        return password.isNotBlank() && password.length >= 5
    }

    private fun isPasswordConformationCorrect(password: String, conformation: String): Boolean {
        return password == conformation
    }
}
