package com.example.music_explorer.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.music_explorer.data.storage.room.entity.Album

@Database(entities = [Album::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        fun buildDataBase(context: Context) = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "AppDataBase"
        ).build()
    }

    abstract fun getAlbumDao(): AlbumDao
}