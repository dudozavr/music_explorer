package com.example.music_explorer.ui.base_music.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.music_explorer.data.storage.preferences.AppPreferences
import com.example.music_explorer.data.storage.preferences.AppPreferencesImpl

class BaseMusicViewModel: ViewModel() {
    val logoutLiveData = MutableLiveData<Unit>()
    private val preferences: AppPreferences = AppPreferencesImpl

    fun initPreferences(context: Context) {
        preferences.initPreferences(context)
    }

    fun logout() {
        preferences.saveToken(0)
        logoutLiveData.postValue(Unit)
    }
}