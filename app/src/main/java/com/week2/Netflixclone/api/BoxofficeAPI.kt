package com.week2.Netflixclone.api

import com.week2.Netflixclone.datas.MovieBoxData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BoxofficeAPI {

    @GET("boxoffice/searchDailyBoxOfficeList.json")
    fun getmoviedata(
        @Query("key") key : String,
        @Query("targetDt") targetDt : String,
        @Query("repNationCd") rep : String?=null,

    ): Call<MovieBoxData>

}