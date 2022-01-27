package com.example.music_explorer.data.storage.room

import androidx.room.*
import com.example.music_explorer.data.storage.room.entity.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>)

    @Update
    fun updateAlbum(album: Album)

    @Query("SELECT * FROM album WHERE name LIKE :albumName")
    fun searchAlbums(albumName: String): List<Album>

    @Query("SELECT * FROM album")
    fun getAlbums(): List<Album>

    @Delete
    fun deleteAlbum(album: Album)

    @Delete
    fun deleteAlbums(vararg album: Album)

    @Query("DELETE FROM album")
    fun deleteAlbums()
}