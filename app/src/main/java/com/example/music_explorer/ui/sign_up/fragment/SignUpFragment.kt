package com.example.music_explorer.ui.sign_up.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.music_explorer.FragmentController
import com.example.music_explorer.R
import com.example.music_explorer.ui.sign_in.fragment.SignInFragment
import com.example.music_explorer.ui.sign_up.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {
    private val signUpViewModel by viewModels<SignUpViewModel>()
    private lateinit var userName: AppCompatEditText
    private lateinit var email: AppCompatEditText
    private lateinit var password: AppCompatEditText
    private lateinit var passwordConformation: AppCompatEditText
    private lateinit var buttonSignUp: AppCompatButton
    private lateinit var signInText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpViewModel.initPreferences(requireContext())
        initFields(view)
        initListeners()
        setEditListeners()
        subscribeOnLiveData()
    }

    private fun initListeners() {
        buttonSignUp.setOnClickListener {
            signUpViewModel.onSignUpClicked(
                email = email.text.toString(),
                name = userName.text.toString(),
                password = password.text.toString(),
                passwordConfirmation = passwordConformation.text.toString()
            )
        }
        signInText.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initFields(view: View) {
        with(view) {
            userName = findViewById(R.id.user_name)
            email = findViewById(R.id.email)
            password = findViewById(R.id.password)
            passwordConformation = findViewById(R.id.password_conformation)
            buttonSignUp = findViewById(R.id.sign_in_button)
            signInText = findViewById(R.id.sign_in_text_view)
        }
    }

    private fun subscribeOnLiveData() {
        signUpViewModel.run {
            isSignUpValidLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    (requireParentFragment() as FragmentController).openFragment(
                        SignInFragment(),
                        true
                    )
                }
            }
            isSignUpInvalidLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(
                        context,
                        getString(R.string.text_for_invalid_sign_up),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            userNameLiveData.observe(viewLifecycleOwner) {
                userName.setText(it)
                userName.setSelection(it.length)
            }
            emailLiveData.observe(viewLifecycleOwner) {
                email.setText(it)
                email.setSelection(it.length)
            }
            passwordLiveData.observe(viewLifecycleOwner) {
                password.setText(it)
                password.setSelection(it.length)
            }
            passwordConfirmationLiveData.observe(viewLifecycleOwner) {
                passwordConformation.setText(it)
                passwordConformation.setSelection(it.length)
            }
        }
    }

    private fun setEditListeners() {
        userName.addTextChangedListener {
            signUpViewModel.updateUserName(it?.toString() ?: "")
        }
        email.addTextChangedListener {
            signUpViewModel.updateEmail(it?.toString() ?: "")
        }
        password.addTextChangedListener {
            signUpViewModel.updatePassword(it?.toString() ?: "")
        }
        passwordConformation.addTextChangedListener {
            signUpViewModel.updatePasswordConfirmation(it?.toString() ?: "")
        }
    }
}