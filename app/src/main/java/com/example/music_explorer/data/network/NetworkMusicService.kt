package com.example.music_explorer.data.network

import com.example.music_explorer.data.entity.Album

interface NetworkMusicService {

    fun getFavoriteAlbums(): List<Album>

    fun getRecommendedAlbums(): List<Album>
}