package com.example.music_explorer.model

class MainModel {

    fun onSignOpClicked(
        email: String,
        name: String,
        password: String,
        passwordConfirmation: String
    ) = name.isNotBlank()
            && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            && password.isNotBlank()
            && password.length >= 5
            && password == passwordConfirmation
}
