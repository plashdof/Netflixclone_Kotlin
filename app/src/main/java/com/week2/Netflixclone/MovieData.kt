package com.week2.Netflixclone


data class MovieData(
    var items: List<Items>
)

data class Items(
    var title: String = "",
    var image: String = "",
)