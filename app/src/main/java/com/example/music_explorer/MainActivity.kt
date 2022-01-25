package com.example.music_explorer

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.music_explorer.data.storage.preferences.AppPreferences
import com.example.music_explorer.data.storage.preferences.AppPreferencesImpl
import com.example.music_explorer.ui.albums.fragment.ListOfMusicFragment
import com.example.music_explorer.ui.base_auth.fragment.BaseAuthFragment
import com.example.music_explorer.ui.base_music.fragment.BaseMusicFragment
import com.example.music_explorer.ui.explore_tab.fragment.ExploreTab
import com.example.music_explorer.ui.library_tab.fragment.LibraryTab
import com.example.music_explorer.ui.sign_up.fragment.SignUpFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), FragmentController {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val preferences: AppPreferences = AppPreferencesImpl
            preferences.initPreferences(this)
            if (preferences.getToken() > 0) {
                openFragment(BaseMusicFragment())
            } else {
                openFragment(BaseAuthFragment())
            }
        }
    }

    override fun openFragment(
        fragment: Fragment,
        doClearBackStack: Boolean
    ) {
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.main_fragment_container, fragment, fragment.toString())
            addToBackStack(null)
            setPrimaryNavigationFragment(fragment)
        }
    }

    override fun onBackPressed() {
        (supportFragmentManager.primaryNavigationFragment?.childFragmentManager
            ?: supportFragmentManager).also {
            if (it.backStackEntryCount > 1) {
                super.onBackPressed()
            } else {
                finish()
            }
        }
    }
}