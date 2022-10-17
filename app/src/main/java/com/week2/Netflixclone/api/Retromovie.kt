package com.week2.Netflixclone.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retromovie {

    val CLIENT_ID = "XffP51OYOn4jJVGY2pPj"
    val CLIENT_SECRET = "YPea1k2aoL"
    val BASE_URL_NAVER_API = "https://openapi.naver.com/"
    var api : NaverAPI

    init{
        val buildRetro = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = buildRetro.create(NaverAPI::class.java)
    }

}