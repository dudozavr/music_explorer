package com.example.music_explorer.data.storage.room

import android.content.Context

class AppDataBaseImpl private constructor() : AlbumDataBase {

    companion object {
        private var albumDao: AlbumDao? = null
        private var instance: AppDataBaseImpl? = null

        fun getInstance(context: Context): AppDataBaseImpl {
            if (instance == null) {
                instance = AppDataBaseImpl()
                albumDao = AppDataBase.buildDataBase(context).getAlbumDao()
            }
            return instance!!
        }
    }

    override fun getAlbumDao() = albumDao!!
}