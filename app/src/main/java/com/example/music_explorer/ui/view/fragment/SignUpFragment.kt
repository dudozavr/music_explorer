package com.example.music_explorer.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.music_explorer.R
import com.example.music_explorer.ui.view.activity.MainActivity
import com.example.music_explorer.ui.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var userName: AppCompatEditText
    private lateinit var email: AppCompatEditText
    private lateinit var password: AppCompatEditText
    private lateinit var passwordConformation: AppCompatEditText
    private lateinit var buttonSignUp: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields(view)
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
        refreshEditTexts()
        setEditListeners()
    }

    private fun initFields(view: View) {
        with(view) {
            userName = findViewById(R.id.user_name)
            email = findViewById(R.id.email)
            password = findViewById(R.id.password)
            passwordConformation = findViewById(R.id.password_conformation)
            buttonSignUp = findViewById(R.id.sign_up)
        }
    }

    private fun subscribeOnLiveData() {
        signUpViewModel.isSignUpValidLiveData.observe(viewLifecycleOwner) {
            (activity as MainActivity).openFragment(SuccessFragment())
        }
        signUpViewModel.isSignUpInvalidLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                getString(R.string.text_for_invalid_sign_up),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setEditListeners() {
        userName.addTextChangedListener {
            signUpViewModel.userNameLiveData.value = it?.toString() ?: ""
        }
        email.addTextChangedListener {
            signUpViewModel.emailLiveData.value = it?.toString() ?: ""
        }
        password.addTextChangedListener {
            signUpViewModel.passwordLiveData.value = it?.toString() ?: ""
        }
        passwordConformation.addTextChangedListener {
            signUpViewModel.passwordConfirmationLiveData.value = it?.toString() ?: ""
        }
    }

    private fun refreshEditTexts() {
        userName.setText(signUpViewModel.userNameLiveData.value)
        email.setText(signUpViewModel.emailLiveData.value)
        password.setText(signUpViewModel.passwordLiveData.value)
        passwordConformation.setText(signUpViewModel.passwordConfirmationLiveData.value)
    }
}