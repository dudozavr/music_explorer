package com.example.music_explorer.ui.sign_in.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.music_explorer.FragmentController
import com.example.music_explorer.R
import com.example.music_explorer.ui.base_music.fragment.BaseMusicFragment
import com.example.music_explorer.ui.sign_in.viewmodel.SignInViewModel
import com.example.music_explorer.ui.sign_up.fragment.SignUpFragment

class SignInFragment : Fragment() {

    private val signInViewModel by viewModels<SignInViewModel>()
    private lateinit var signUpTextView: TextView
    private lateinit var saveCredentialsCheckBox: CheckBox
    private lateinit var email: AppCompatEditText
    private lateinit var password: AppCompatEditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInViewModel.initPreferences(requireContext())
        signInViewModel.fetchStoredData()

        initFields()
        initListeners()
        subscribeOnLiveData()
    }

    private fun initListeners() {
        loginButton.setOnClickListener {
            signInViewModel.onSignInClicked(
                email = email.text.toString(),
                password = password.text.toString()
            )
        }
        signUpTextView.setOnClickListener {
            (requireParentFragment() as FragmentController).openFragment(SignUpFragment())
        }
        saveCredentialsCheckBox.setOnCheckedChangeListener { compoundButton, selected ->
            signInViewModel.updateCheckBox(selected)
            signInViewModel.setSaveCredentialSelected(selected)
        }
        email.addTextChangedListener {
            signInViewModel.updateEmail(it?.toString() ?: "")
        }
        password.addTextChangedListener {
            signInViewModel.updatePassword(it?.toString() ?: "")
        }
    }

    private fun initFields() {
        view?.let {
            signUpTextView = it.findViewById(R.id.sign_up_text_view)
            saveCredentialsCheckBox = it.findViewById(R.id.save_credentials_check_box)
            email = it.findViewById(R.id.email)
            password = it.findViewById(R.id.password)
            loginButton = it.findViewById(R.id.sign_in_button)
        }
    }

    private fun subscribeOnLiveData() {
        signInViewModel.run {
            isSignInValidLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    (activity as FragmentController).openFragment(BaseMusicFragment(), true)
                }
            }
            isSignInInvalidLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(
                        context,
                        getString(R.string.text_for_invalid_sign_in),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            checkBoxLiveData.observe(viewLifecycleOwner) { isSelected ->
                saveCredentialsCheckBox.isChecked = isSelected
            }
            emailLiveData.observe(viewLifecycleOwner) { restoredEmail ->
                email.setText(restoredEmail)
                email.setSelection(restoredEmail.length)
            }
            passwordLiveData.observe(viewLifecycleOwner) { restoredPassword ->
                password.setText(restoredPassword)
                password.setSelection(restoredPassword.length)
            }
        }
    }
}