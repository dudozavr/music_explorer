package com.example.music_explorer.model.sign_up

interface SignUpService {

    fun isSignUpValid(
        name: String,
        email: String,
        password: String,
        conformation: String
    ): Boolean
}