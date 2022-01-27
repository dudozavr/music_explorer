package com.example.music_explorer.data.network

import com.example.music_explorer.data.storage.room.entity.Album

class NetworkMusicServiceImpl() : NetworkMusicService {

    override suspend fun getFavoriteAlbums() = listOfFavoritesAlbums

    override suspend fun getRecommendedAlbums() = listOfRecommendedAlbums

    private val listOfFavoritesAlbums = listOf<Album>(
        Album(
            name = "Abbey Road",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/af251669a48a4bafb448e1f6c0de01be.jpg"
        ),
        Album(
            name = "Sgt. Pepper's Lonely Hearts Club Band",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/a4e112b368c5ad405d43f0930617c687.jpg"
        ),
        Album(
            name = "Revolver",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/deaec2d4735bea0d1c45fc75261624ae.jpg"
        ),
        Album(
            name = "Rubber Soul",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/72ed10a859fb4c1fb29a546078ec737d.jpg"
        ),
        Album(
            name = "Let It Be",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/a152d53917b94c968087513a71d6ab3e.jpg"
        ),
        Album(
            name = "Magical Mystery Tour",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/3947ccc7613d4555ad120bf8b5c68e59.jpg"
        ),
        Album(
            name = "1",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/c287f9f433293ca72d9866fb18b460a5.jpg"
        ),
        Album(
            name = "Help!",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/23c5e3b982d441d690315efa25844ddd.jpg"
        ),
        Album(
            name = "With The Beatles",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/dc93a04aab74427ab867aee61f00e6bb.jpg"
        )
    )

    private val listOfRecommendedAlbums = listOf(
        Album(
            name = "Nevermind",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/570021b68d3d9d2db08bc99a473303b0.jpg"
        ),
        Album(
            name = "Unplugged in New York",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/5e783f27dee44b6d94eea9ce0732b317.jpg"
        ),
        Album(
            name = "In Utero",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/f87609e9ecee469f940f4dd208f229b9.jpg"
        ),
        Album(
            name = "Nirvana",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/573899052466479da97b9207a780d06f.jpg"
        ),
        Album(
            name = "Bleach",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/6cb91d2c3e554b028133947dfae73b43.jpg"
        ),
        Album(
            name = "Incesticide",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/a78bbd5ff0184115902f403212f04976.jpg"
        ),
        Album(
            name = "With the Lights Out",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/83222b653ba5456dbd61cde3a9a108fc.jpg"
        )
    )
}