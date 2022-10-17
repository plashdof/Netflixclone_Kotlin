package com.week2.Netflixclone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.adapter.NewhotAdapter
import com.week2.Netflixclone.api.Videos
import com.week2.Netflixclone.databinding.FragmentNewhotBinding
import com.week2.Netflixclone.datas.VideoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewhotFragment : Fragment() {
    private lateinit var adapter : NewhotAdapter
    private lateinit var binding : FragmentNewhotBinding
    val videoarr : ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewhotBinding.inflate(layoutInflater)

        Videos.api.getYoutubeVideo(Videos.API_KEY,"id", Videos.CHANNEL_URL,10)
            .enqueue(object : Callback<VideoData> {
                override fun onResponse(call: Call<VideoData>, response: Response<VideoData>) {

                    val size = response.body()!!.items.size
                    Log.d("결과","${response.raw()}")
                    for(i in 0 until size){
                        videoarr.add("https://www.youtube.com/embed/"+response.body()!!.items[i].id.videoId)
                    }

                    recycler()

                }
                override fun onFailure(call: Call<VideoData>, t: Throwable) {

                }
            })


        return binding.root
    }

    private fun recycler() {
        val newhotAdapter = NewhotAdapter(videoarr)
        binding.newhotRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.newhotRecycler.adapter = newhotAdapter

    }




}