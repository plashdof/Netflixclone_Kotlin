package com.week2.Netflixclone.datas

data class VideoData(
    val kind : String,
    val etag : String,
    val nextPageToken : String,
    val regionCode : String,
    val pageInfo :pageinfo,
    val items : ArrayList<vitems>


)
data class pageinfo(
    val totalResults : Int,
    val resultsPerPage : Int
)

data class vitems(
    val kind : String,
    val etag : String,
    val id : video
)

data class video(
    val kind : String,
    val videoId : String
)