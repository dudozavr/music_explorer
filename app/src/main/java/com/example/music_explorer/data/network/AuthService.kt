package com.example.music_explorer.data.network

interface AuthService {

    fun isSignUpValid(
        name: String,
        email: String,
        password: String,
        conformation: String
    ): Boolean

    fun isSignInValid(
        email: String,
        password: String,
    ): Boolean

    fun getToken(): Int
}