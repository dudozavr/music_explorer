package com.example.music_explorer.ui.base_auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.music_explorer.FragmentController
import com.example.music_explorer.R
import com.example.music_explorer.ui.sign_in.fragment.SignInFragment

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
        openFragment(SignInFragment())
    }

    override fun openFragment(fragment: Fragment, doClearBackStack: Boolean) {
        if (doClearBackStack) {
            clearBackStack()
        }
        childFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.base_auth_fragment_container, fragment, fragment.toString())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun clearBackStack(backStackName: String? = null) {
        childFragmentManager.popBackStack(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}