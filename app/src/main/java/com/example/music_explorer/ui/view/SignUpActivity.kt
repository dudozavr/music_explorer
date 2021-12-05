package com.example.music_explorer.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.music_explorer.R
import com.example.music_explorer.ui.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSignUp: AppCompatButton = findViewById(R.id.sign_up)
        val userName: AppCompatEditText = findViewById(R.id.user_name)
        val email: AppCompatEditText = findViewById(R.id.email)
        val password: AppCompatEditText = findViewById(R.id.password)
        val passwordConformation: AppCompatEditText = findViewById(R.id.password_conformation)
        ViewModelProvider(owner = this)[SignUpViewModel::class.java].also { signUpViewModel = it }

        buttonSignUp.setOnClickListener {
            signUpViewModel.onSignOpClicked(
                email = email.text.toString(),
                name = userName.text.toString(),
                password = password.text.toString(),
                passwordConfirmation = passwordConformation.text.toString()
            )
        }
        subscribeOnLiveData()
    }

    private fun subscribeOnLiveData() {
        signUpViewModel.isSignUpValidLiveData.observe(this) {
            startActivity(Intent(this, SuccessActivity::class.java))
        }
        signUpViewModel.isSignUpInvalidLiveData.observe(this) {
            Toast.makeText(this, getString(R.string.text_for_invalid_sign_up), Toast.LENGTH_SHORT)
                .show()
        }
    }
}