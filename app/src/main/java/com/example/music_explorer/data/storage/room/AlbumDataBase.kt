package com.example.music_explorer.data.storage.room

interface AlbumDataBase {
    fun getAlbumDao(): AlbumDao
}