package com.example.music_explorer.ui.albums.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.music_explorer.R
import com.example.music_explorer.data.entity.Album

class AlbumViewHolder(private val view: View, val selectedItem: (Album) -> Unit) :
    ViewHolder(view) {

    private var album: Album? = null

    init {
        view.setOnClickListener {
            album?.let {
                selectedItem(it)
            }
        }
    }

    fun setAlbum(album: Album) {
        val image = view.findViewById<ImageView>(R.id.album_logo)

        this.album = album
        view.findViewById<TextView>(R.id.album_name).text = album.name
        Glide.with(view.context).load(album.imageUrl).into(image)
    }
}