package com.example.music_explorer.ui.albums.viewmodel

import androidx.lifecycle.*
import com.example.music_explorer.data.entity.Album
import com.example.music_explorer.data.network.NetworkMusicService
import com.example.music_explorer.data.network.NetworkMusicServiceImpl

class AlbumListViewModel : ViewModel(), LifecycleEventObserver {
    val favoriteAlbums = MutableLiveData<List<Album>>()
    val recommendedAlbums = MutableLiveData<List<Album>>()
    private val networkMusicService: NetworkMusicService = NetworkMusicServiceImpl()

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

    private fun getAlbums() {
        favoriteAlbums.postValue(networkMusicService.getFavoriteAlbums())
        recommendedAlbums.postValue(networkMusicService.getRecommendedAlbums())
    }
}