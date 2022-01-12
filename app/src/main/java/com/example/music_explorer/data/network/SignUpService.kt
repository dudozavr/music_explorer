package com.example.music_explorer.data.network

interface SignUpService {

    fun isSignUpValid(
        name: String,
        email: String,
        password: String,
        conformation: String
    ): Boolean
}