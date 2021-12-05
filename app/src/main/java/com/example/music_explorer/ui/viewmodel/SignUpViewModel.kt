package com.example.music_explorer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.music_explorer.model.sign_up.SignUpService
import com.example.music_explorer.model.sign_up.SignUpServiceImpl

class SignUpViewModel : ViewModel() {
    val isSignUpValidLiveData = MutableLiveData<Unit>()
    val isSignUpInvalidLiveData = MutableLiveData<Unit>()
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
        isSignUpValidLiveData.postValue(Unit)
    } else {
        isSignUpInvalidLiveData.postValue(Unit)
    }
}