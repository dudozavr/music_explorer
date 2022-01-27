package com.example.music_explorer.ui.albums.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_explorer.R
import com.example.music_explorer.data.storage.room.entity.Album

class AlbumRecyclerViewAdapter(
    private val albums: List<Album>,
    private val selectedItem: (Album) -> Unit
) : RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AlbumViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_album_item, parent, false)
        return AlbumViewHolder(view, selectedItem)
    }

    override fun onBindViewHolder(viewHolder: AlbumViewHolder, position: Int) {
        viewHolder.setAlbum(albums[position])
    }

    override fun getItemCount() = albums.size
}