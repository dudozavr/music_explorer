package com.example.music_explorer.data.network

import com.example.music_explorer.data.storage.room.entity.Album

interface NetworkMusicService {

    suspend fun getFavoriteAlbums(): List<Album>

    suspend fun getRecommendedAlbums(): List<Album>
}