package com.week2.Netflixclone

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("image")
    val image: String
)
