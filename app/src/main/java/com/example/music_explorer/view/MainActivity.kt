package com.example.music_explorer.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.example.music_explorer.R
import com.example.music_explorer.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSignUp: AppCompatButton = findViewById(R.id.sign_up)
        val userName: AppCompatEditText = findViewById(R.id.user_name)
        val email: AppCompatEditText = findViewById(R.id.email)
        val password: AppCompatEditText = findViewById(R.id.password)
        val passwordConformation: AppCompatEditText = findViewById(R.id.password_conformation)

        ViewModelProvider(this)[MainViewModel::class.java].also { viewModel = it }
        buttonSignUp.setOnClickListener {
            viewModel.onSignOpClicked(
                email.text.toString(),
                userName.text.toString(),
                password.text.toString(),
                passwordConformation.text.toString()
            )
        }
        subscribeOnLiveData()
    }

    private fun subscribeOnLiveData() {
        viewModel.isSignUpSuccessLiveData.observe(this) {
            startActivity(Intent(this, SignUpSuccessActivity::class.java))
        }
        viewModel.isSignUpFailedLiveData.observe(this) {
            Toast.makeText(this, "Something was wrong", Toast.LENGTH_SHORT).show()
        }
    }
}