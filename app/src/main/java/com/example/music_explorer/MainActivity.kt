package com.example.music_explorer

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.music_explorer.ui.albums.fragment.ListOfMusicFragment
import com.example.music_explorer.ui.base_auth.fragment.BaseAuthFragment
import com.example.music_explorer.ui.explore_tab.fragment.ExploreTab
import com.example.music_explorer.ui.library_tab.fragment.LibraryTab
import com.example.music_explorer.ui.sign_up.fragment.SignUpFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), FragmentController{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            openFragment(BaseAuthFragment())
        }
    }

    override fun openFragment(
        fragment: Fragment,
        doClearBackStack: Boolean
    ) {
        if (doClearBackStack) {
            clearBackStack()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, fragment.toString())
            .addToBackStack(null)
            .commit()
    }

    private fun clearBackStack(backStackName: String? = null) {
        supportFragmentManager.popBackStack(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }
}