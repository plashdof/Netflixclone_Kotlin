package com.week2.Netflixclone.datas

data class MovieBoxData(
    val boxOfficeResult : MovieBoxDetail
)

data class MovieBoxDetail(
    val boxofficeType : String,
    val showRange : String,
    val dailyBoxOfficeList : ArrayList<MovieBoxRankings>
)


data class MovieBoxRankings(
    val rnum : String,
    val rank : String,
    val movieNm : String,
)
