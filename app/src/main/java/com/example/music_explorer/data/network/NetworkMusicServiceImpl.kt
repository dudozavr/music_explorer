package com.example.music_explorer.data.network

import com.example.music_explorer.data.entity.Album

class NetworkMusicServiceImpl() : NetworkMusicService {

    override fun getFavoriteAlbums() = listOfFavoritesAlbums

    override fun getRecommendedAlbums() = listOfRecommendedAlbums

    private val listOfFavoritesAlbums = listOf<Album>(
        Album(
            "Abbey Road",
            "https://lastfm.freetls.fastly.net/i/u/770x0/af251669a48a4bafb448e1f6c0de01be.jpg"
        ),
        Album(
            "Sgt. Pepper's Lonely Hearts Club Band",
            "https://lastfm.freetls.fastly.net/i/u/770x0/a4e112b368c5ad405d43f0930617c687.jpg"
        ),
        Album(
            "Revolver",
            "https://lastfm.freetls.fastly.net/i/u/770x0/deaec2d4735bea0d1c45fc75261624ae.jpg"
        ),
        Album(
            "Rubber Soul",
            "https://lastfm.freetls.fastly.net/i/u/770x0/72ed10a859fb4c1fb29a546078ec737d.jpg"
        ),
        Album(
            "Let It Be",
            "https://lastfm.freetls.fastly.net/i/u/770x0/a152d53917b94c968087513a71d6ab3e.jpg"
        ),
        Album(
            "Magical Mystery Tour",
            "https://lastfm.freetls.fastly.net/i/u/770x0/3947ccc7613d4555ad120bf8b5c68e59.jpg"
        ),
        Album(
            "1",
            "https://lastfm.freetls.fastly.net/i/u/770x0/c287f9f433293ca72d9866fb18b460a5.jpg"
        ),
        Album(
            "Help!",
            "https://lastfm.freetls.fastly.net/i/u/770x0/23c5e3b982d441d690315efa25844ddd.jpg"
        ),
        Album(
            "With The Beatles",
            "https://lastfm.freetls.fastly.net/i/u/770x0/dc93a04aab74427ab867aee61f00e6bb.jpg"
        )
    )

    private val listOfRecommendedAlbums = listOf(
        Album(
            "Nevermind",
            "https://lastfm.freetls.fastly.net/i/u/770x0/570021b68d3d9d2db08bc99a473303b0.jpg"
        ),
        Album(
            "Unplugged in New York",
            "https://lastfm.freetls.fastly.net/i/u/770x0/5e783f27dee44b6d94eea9ce0732b317.jpg"
        ),
        Album(
            "In Utero",
            "https://lastfm.freetls.fastly.net/i/u/770x0/f87609e9ecee469f940f4dd208f229b9.jpg"
        ),
        Album(
            "Nirvana",
            "https://lastfm.freetls.fastly.net/i/u/770x0/573899052466479da97b9207a780d06f.jpg"
        ),
        Album(
            "Bleach",
            "https://lastfm.freetls.fastly.net/i/u/770x0/6cb91d2c3e554b028133947dfae73b43.jpg"
        ),
        Album(
            "Incesticide",
            "https://lastfm.freetls.fastly.net/i/u/770x0/a78bbd5ff0184115902f403212f04976.jpg"
        ),
        Album(
            "With the Lights Out",
            "https://lastfm.freetls.fastly.net/i/u/770x0/83222b653ba5456dbd61cde3a9a108fc.jpg"
        )
    )
}