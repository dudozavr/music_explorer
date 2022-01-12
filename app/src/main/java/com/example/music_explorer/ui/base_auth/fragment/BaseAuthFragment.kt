package com.example.music_explorer.ui.base_auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.music_explorer.FragmentController
import com.example.music_explorer.MainActivity
import com.example.music_explorer.R
import com.example.music_explorer.ui.sign_up.fragment.SignUpFragment

class BaseAuthFragment : Fragment(), FragmentController {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_auth_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFragment(SignUpFragment())
    }

    override fun openFragment(fragment: Fragment, doClearBackStack: Boolean) {
        if (doClearBackStack) {
            clearBackStack()
        }
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.base_auth_fragment_container, fragment, fragment.toString())
            .addToBackStack(null)
            .commit()
    }

    private fun clearBackStack(backStackName: String? = null) {
        parentFragmentManager.popBackStack(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}