package com.week2.Netflixclone

import retrofit2.Call
import retrofit2.http.*

interface NaverAPI {

    @GET("v1/search/movie.json")
    fun getMoviePoster(
        @Header("X-Naver-Client-Id") clientId : String,
        @Header("X-Naver-Client-Secret") clientSecret : String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null,
        @Query("genre") genre: String? = null
    ): Call<MovieData>
}