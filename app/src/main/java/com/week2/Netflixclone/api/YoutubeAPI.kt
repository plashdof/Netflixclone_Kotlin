package com.week2.Netflixclone.api


import com.week2.Netflixclone.datas.VideoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YoutubeAPI {

    @GET("v3/search")
    fun getYoutubeVideo(

        @Query("key") key : String,
        @Query("part") part : String,
        @Query("channelId") channel : String? = null,
        @Query("maxResults") maxresult : Int? = null,
    ): Call<VideoData>
}