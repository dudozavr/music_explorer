package com.example.music_explorer.model.sign_up

import android.util.Patterns

class SignUpServiceImpl : SignUpService {
    override fun isSignUpValid(
        name: String,
        email: String,
        password: String,
        conformation: String
    ) = isUserNameCorrect(name = name)
            && isEmailCorrect(email = email)
            && isPasswordCorrect(password = password)
            && isPasswordConformationCorrect(password = password, conformation = conformation)

    private fun isUserNameCorrect(name: String) = name.isNotBlank()

    private fun isEmailCorrect(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isPasswordCorrect(password: String): Boolean {
        return password.isNotBlank() && password.length >= 5
    }

    private fun isPasswordConformationCorrect(password: String, conformation: String): Boolean {
        return password == conformation
    }
}
