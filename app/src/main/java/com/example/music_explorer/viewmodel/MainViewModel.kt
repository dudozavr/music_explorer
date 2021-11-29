package com.example.music_explorer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.music_explorer.model.MainModel

class MainViewModel : ViewModel() {
    val isSignUpSuccessLiveData = MutableLiveData<Unit>()
    val isSignUpFailedLiveData = MutableLiveData<Unit>()
    private val mainModel = MainModel()

    fun onSignOpClicked(
        email: String,
        name: String,
        password: String,
        passwordConfirmation: String
    ) = if (mainModel.onSignOpClicked(email, name, password, passwordConfirmation)) {
        isSignUpSuccessLiveData.postValue(Unit)
    } else {
        isSignUpFailedLiveData.postValue(Unit)
    }
}