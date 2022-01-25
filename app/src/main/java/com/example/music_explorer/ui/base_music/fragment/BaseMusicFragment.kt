package com.example.music_explorer.ui.base_music.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.music_explorer.FragmentController
import com.example.music_explorer.MainActivity
import com.example.music_explorer.R
import com.example.music_explorer.ui.albums.fragment.ListOfMusicFragment
import com.example.music_explorer.ui.base_auth.fragment.BaseAuthFragment
import com.example.music_explorer.ui.base_music.viewmodel.BaseMusicViewModel
import com.example.music_explorer.ui.explore_tab.fragment.ExploreTab
import com.example.music_explorer.ui.library_tab.fragment.LibraryTab
import com.google.android.material.navigation.NavigationBarView

class BaseMusicFragment : Fragment(), FragmentController {

    private val baseMusicViewModel by viewModels<BaseMusicViewModel>()
    private lateinit var bottomNavigationView: NavigationBarView
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.base_music_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseMusicViewModel.initPreferences(requireContext())

        customizeBottomNavigationView()
        customizeAlbumToolbar()
        subscribeOnLiveData()
        openFragment(ListOfMusicFragment())
    }

    private fun subscribeOnLiveData() {
        baseMusicViewModel.logoutLiveData.observe(viewLifecycleOwner) {
            (requireActivity() as FragmentController).openFragment(BaseAuthFragment(), true)
        }
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
            replace(R.id.base_music_fragment_container, fragment, fragment.toString())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun clearBackStack(backStackName: String? = null) {
        parentFragmentManager.popBackStack(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun customizeBottomNavigationView() {
        bottomNavigationView = requireView().findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> openFragment(ListOfMusicFragment(), true)
                R.id.explore_nav -> openFragment(ExploreTab(), true)
                R.id.library_nav -> openFragment(LibraryTab(), true)
            }
            true
        }
    }

    private fun customizeAlbumToolbar() {
        toolbar = requireView().findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.album_toolbar_menu)
        setToolbarClickListeners()
    }

    private fun setToolbarClickListeners() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search_button -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_implemented_text),
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.log_out -> {
                    baseMusicViewModel.logout()
                    true
                }
                else -> true
            }
        }
    }
}