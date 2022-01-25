package com.example.music_explorer.ui.sign_up.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.music_explorer.data.network.AuthService
import com.example.music_explorer.data.network.AuthServiceImpl
import com.example.music_explorer.data.storage.preferences.AppPreferences
import com.example.music_explorer.data.storage.preferences.AppPreferencesImpl

class SignUpViewModel : ViewModel() {
    val isSignUpValidLiveData = MutableLiveData<Boolean>()
    val isSignUpInvalidLiveData = MutableLiveData<Boolean>()
    val userNameLiveData = MutableLiveData<String>()
    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordConfirmationLiveData = MutableLiveData<String>()
    private val authService: AuthService = AuthServiceImpl()
    private val preferences: AppPreferences = AppPreferencesImpl

    fun onSignUpClicked(
        email: String,
        name: String,
        password: String,
        passwordConfirmation: String
    ) = if (authService.isSignUpValid(
            email = email,
            name = name,
            password = password,
            conformation = passwordConfirmation
        )
    ) {
        processSignUpSuccess(email, password)
        isSignUpValidLiveData.postValue(true)
    } else {
        isSignUpInvalidLiveData.postValue(true)
    }

    fun initPreferences(context: Context) {
        preferences.initPreferences(context)
    }

    private fun processSignUpSuccess(email: String, password: String) {
        preferences.run {
            saveLogin(email)
            savePassword(password)
        }
    }

    fun updateUserName(userName: String) {
        userNameLiveData.run{
            if (userName != value) {
                value = userName
            }
        }
    }

    fun updateEmail(email: String) {
        emailLiveData.run {
            if (email != value) {
                value = email
            }
        }
    }

    fun updatePassword(password: String) {
        passwordLiveData.run {
            if (password != value) {
                value = password
            }
        }
    }

    fun updatePasswordConfirmation(passwordConfirmation: String) {
        passwordConfirmationLiveData.run {
            if (passwordConfirmation != value) {
                value = passwordConfirmation
            }
        }
    }
}