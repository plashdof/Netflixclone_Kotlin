package com.week2.Netflixclone.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retroboxoffice {

    val SERVICE_KEY = "f7625c8604721c1f15ffcf29228d617e"
    val BASE_URL_BOX_OFFICE = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/"
    var api : BoxofficeAPI

    init{
        val buildBoxRetro = Retrofit.Builder()
            .baseUrl(BASE_URL_BOX_OFFICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = buildBoxRetro.create(BoxofficeAPI::class.java)
    }

}