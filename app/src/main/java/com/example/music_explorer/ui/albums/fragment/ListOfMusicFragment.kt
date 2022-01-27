package com.example.music_explorer.ui.albums.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_explorer.R
import com.example.music_explorer.data.storage.room.AppDataBaseImpl
import com.example.music_explorer.ui.albums.viewmodel.AlbumListViewModel

class ListOfMusicFragment : Fragment() {

    companion object {
        const val TAG = "ListOfMusicFragment"
    }

    private val viewModel by viewModels<AlbumListViewModel>()
    private lateinit var favouritesAlbumsRecycleView: RecyclerView
    private lateinit var recommendedAlbumsRecycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_music_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setAlbumDao(AppDataBaseImpl.getInstance(requireContext()).getAlbumDao())

        favouritesAlbumsRecycleView = view.findViewById(R.id.list_of_favourites_albums)
        customizeRecycleView(favouritesAlbumsRecycleView)
        recommendedAlbumsRecycleView = view.findViewById(R.id.list_of_recommended_albums)
        customizeRecycleView(recommendedAlbumsRecycleView)

        lifecycle.addObserver(viewModel)
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.favoriteAlbumsLiveData.observe(viewLifecycleOwner, { albums ->
            favouritesAlbumsRecycleView.adapter = AlbumRecyclerViewAdapter(albums) { album ->
                Log.d(TAG, album.toString())
            }
        })
        viewModel.recommendedAlbumsLiveData.observe(viewLifecycleOwner, { albums ->
            recommendedAlbumsRecycleView.adapter = AlbumRecyclerViewAdapter(albums) { album ->
                Log.d(TAG, album.toString())
            }
        })
    }

    private fun customizeRecycleView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            addItemDecoration(AlbumItemDecoration())
        }
    }
}