package com.example.music_explorer.ui.sign_in.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.music_explorer.data.network.AuthService
import com.example.music_explorer.data.network.AuthServiceImpl
import com.example.music_explorer.data.storage.preferences.AppPreferences
import com.example.music_explorer.data.storage.preferences.AppPreferencesImpl

class SignInViewModel : ViewModel() {
    val isSignInValidLiveData = MutableLiveData<Boolean>()
    val isSignInInvalidLiveData = MutableLiveData<Boolean>()
    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val checkBoxLiveData = MutableLiveData<Boolean>()

    private val authService: AuthService = AuthServiceImpl()
    private val preferences: AppPreferences = AppPreferencesImpl

    fun onSignInClicked(
        email: String,
        password: String,
    ) = if (authService.isSignInValid(
            email = email,
            password = password,
        )
    ) {
        processLoginSuccess(authService.getToken())
        isSignInValidLiveData.postValue(true)
    } else {
        isSignInInvalidLiveData.postValue(true)
    }

    fun setSaveCredentialSelected(isSelected: Boolean) {
        preferences.setSaveCredentialSelected(isSelected)
    }

    fun initPreferences(context: Context) {
        preferences.initPreferences(context)
    }

    fun fetchStoredData() {
        preferences.run {
            if (isSaveCredentialsSelected()) {
                emailLiveData.postValue(getLogin())
                passwordLiveData.postValue(getPassword())
                checkBoxLiveData.postValue(isSaveCredentialsSelected())
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

    fun updateCheckBox(isSelected: Boolean) {
        checkBoxLiveData.run {
            if (isSelected != value) {
                value = isSelected
            }
        }
    }

    private fun processLoginSuccess(token: Int) {
        preferences.run {
            if (isSaveCredentialsSelected()) {
                saveToken(token)
            }
            setSaveCredentialSelected(isSaveCredentialsSelected())
        }
    }
}