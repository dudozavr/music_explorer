package com.example.music_explorer

import androidx.fragment.app.Fragment

interface FragmentController {
    fun openFragment(fragment: Fragment, doClearBackStack: Boolean = false)
}