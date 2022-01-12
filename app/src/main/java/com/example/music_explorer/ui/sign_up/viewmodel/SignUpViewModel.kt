package com.example.music_explorer.ui.sign_up.viewmodel

import androidx.lifecycle.*
import com.example.music_explorer.data.network.SignUpService
import com.example.music_explorer.data.network.SignUpServiceImpl

class SignUpViewModel : ViewModel() {
    val isSignUpValidLiveData = MutableLiveData<Boolean>()
    val isSignUpInvalidLiveData = MutableLiveData<Boolean>()
    val userNameLiveData = MutableLiveData<String>()
    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordConfirmationLiveData = MutableLiveData<String>()
    private val signUpService: SignUpService = SignUpServiceImpl()

    fun onSignOpClicked(
        email: String,
        name: String,
        password: String,
        passwordConfirmation: String
    ) = if (signUpService.isSignUpValid(
            email = email,
            name = name,
            password = password,
            conformation = passwordConfirmation
        )
    ) {
        isSignUpValidLiveData.postValue(true)
    } else {
        isSignUpInvalidLiveData.postValue(true)
    }
}