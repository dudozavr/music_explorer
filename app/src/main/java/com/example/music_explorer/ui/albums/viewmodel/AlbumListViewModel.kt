package com.example.music_explorer.ui.albums.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.music_explorer.data.storage.room.entity.Album
import com.example.music_explorer.data.network.NetworkMusicService
import com.example.music_explorer.data.network.NetworkMusicServiceImpl
import com.example.music_explorer.data.storage.room.AlbumDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumListViewModel : ViewModel(), LifecycleEventObserver {

    companion object {
        private const val ERROR_TAG = "AlbumListViewModel"
    }

    val favoriteAlbumsLiveData = MutableLiveData<List<Album>>()
    val recommendedAlbumsLiveData = MutableLiveData<List<Album>>()
    private val networkMusicService: NetworkMusicService = NetworkMusicServiceImpl()
    private lateinit var albumDao: AlbumDao

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                getAlbums()
            }
            Lifecycle.Event.ON_START -> {
                println("ON_START")
            }
            Lifecycle.Event.ON_RESUME -> {
                println("ON_RESUME")
            }
            Lifecycle.Event.ON_PAUSE -> {
                println("ON_PAUSE")
            }
            Lifecycle.Event.ON_STOP -> {
                println("ON_STOP")
            }
            Lifecycle.Event.ON_DESTROY -> {
                println("ON_DESTROY")
            }
            Lifecycle.Event.ON_ANY -> {
                println("ON_ANY")
            }
        }
    }

    fun setAlbumDao(albumDao: AlbumDao) {
        this.albumDao = albumDao
    }

    private fun getAlbums() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                networkMusicService.getFavoriteAlbums().also {
                    cacheAlbums(it)
                    favoriteAlbumsLiveData.postValue(it)
                }
                networkMusicService.getRecommendedAlbums().also {
                    cacheAlbums(it)
                    recommendedAlbumsLiveData.postValue(it)
                }
            } catch (exception: Exception) {
                Log.e(ERROR_TAG, exception.message ?: "")
            }
        }
    }

    private suspend fun cacheAlbums(albums: List<Album>) {
        albumDao.insertAlbums(albums)
    }
}