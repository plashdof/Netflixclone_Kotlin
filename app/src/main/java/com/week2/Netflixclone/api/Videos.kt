package com.week2.Netflixclone.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Videos {

    val API_KEY = "AIzaSyAMuCJkM5epgBreyEf83kEraHO5UfO7lCI"
    val CHANNEL_URL = "UCaHGOzOyeYzLQeKsVkfLEGA"
    val BASE_URL_YOUTUBE_API = "https://www.googleapis.com/youtube/"
    var api : YoutubeAPI

    init{
        val buildRetro = Retrofit.Builder()
            .baseUrl(BASE_URL_YOUTUBE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = buildRetro.create(YoutubeAPI::class.java)
    }

}