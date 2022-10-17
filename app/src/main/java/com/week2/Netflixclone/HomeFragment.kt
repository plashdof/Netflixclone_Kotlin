package com.week2.Netflixclone


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.api.Retroboxoffice.SERVICE_KEY
import com.week2.Netflixclone.api.Retromovie.CLIENT_ID
import com.week2.Netflixclone.api.Retromovie.CLIENT_SECRET
import com.week2.Netflixclone.adapter.MovieAdapter
import com.week2.Netflixclone.api.Retroboxoffice
import com.week2.Netflixclone.api.Retromovie
import com.week2.Netflixclone.databinding.FragmentHomeBinding
import com.week2.Netflixclone.datas.MovieBoxData
import com.week2.Netflixclone.datas.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment() : Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentHomeBinding

    val top10data: ArrayList<String> = arrayListOf()
    val top10dataImg: ArrayList<String> = arrayListOf()

    val koreandata: ArrayList<String> = arrayListOf()
    val thrillerdata: ArrayList<String> = arrayListOf()
    val romancedata: ArrayList<String> = arrayListOf()
    val animationdata: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        getmoviename(top10data, top10dataImg, "top10")

        binding.homeSearchBtn.setOnClickListener {
            Log.d("dddd", "clicked")
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.homeProfileBtn.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }

        koreandata.apply{
            for(i in 0 until 5){
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213481_P13_180201.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2133/213383_P01_171412.png")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213487_P17_153028.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2198/219834_P13_110945.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2200/220044_P12_163612.jpg")
            }
        }

        thrillerdata.apply{
            for(i in 0 until 5){
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2200/220044_P12_163612.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213481_P13_180201.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213487_P17_153028.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2133/213383_P01_171412.png")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2198/219834_P13_110945.jpg")

            }
        }

        romancedata.apply{
            for(i in 0 until 5){
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2198/219834_P13_110945.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2200/220044_P12_163612.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2133/213383_P01_171412.png")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213481_P13_180201.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213487_P17_153028.jpg")
            }
        }

        animationdata.apply{
            for(i in 0 until 5){
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213481_P13_180201.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2200/220044_P12_163612.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2198/219834_P13_110945.jpg")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2133/213383_P01_171412.png")
                add("https://ssl.pstatic.net/imgmovie/mdi/mit110/2134/213487_P17_153028.jpg")
            }
        }

        recycler()



        return binding.root
    }


    private fun getmoviename(
        targetdata: ArrayList<String>,
        targetimg: ArrayList<String>,
        targetrecycler: String
    ) {
        Retroboxoffice.api.getmoviedata(SERVICE_KEY, "20221001")
            .enqueue(object : Callback<MovieBoxData> {
                override fun onResponse(
                    call: Call<MovieBoxData>,
                    response: Response<MovieBoxData>
                ) {
                    Log.d("결과", "${response.raw()}")
                    Log.d("결과", "${response.body()}")
                    for (i in 0 until 10) {
                        top10data.add(response.body()!!.boxOfficeResult.dailyBoxOfficeList[i].movieNm)
                    }

                    for (i in 0 until 10) {
                        getImage(targetdata[i], targetimg, "$targetrecycler")
                    }
                }

                override fun onFailure(call: Call<MovieBoxData>, t: Throwable) {
                    Log.d("결과:", "실패 : $t")
                }
            })
    }

    private fun getImage(movienm: String, targetarr: ArrayList<String>, targetrecycler: String) {
        Retromovie.api.getMoviePoster(CLIENT_ID, CLIENT_SECRET, "$movienm", 1, 1)
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(
                    call: Call<MovieData>,
                    response: Response<MovieData>
                ) {
                    val size = response.body()!!.items.size
                    Log.d("결과:", "${response.raw()}")

                    if (size != 0) {
                        for (i in 0 until size) {
                            if (response.body()!!.items[i].image != "") {
                                targetarr.add(response.body()!!.items[i].image)
                            }
                        }
                        if (targetrecycler == "top10") top10recycler()

                    }
                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.d("결과:", "실패 : $t")
                }
            })
    }

    private fun top10recycler() {
        val top10Adapter = MovieAdapter(top10dataImg)
        binding.homeRecyclerTop10.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerTop10.adapter = top10Adapter

    }

    private fun recycler() {
        val koreanAdapter = MovieAdapter(koreandata)
        binding.homeRecyclerKorean.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerKorean.adapter = koreanAdapter

        val thrillerAdapter = MovieAdapter(thrillerdata)
        binding.homeRecyclerThriller.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerThriller.adapter = thrillerAdapter

        val romanceAdapter = MovieAdapter(romancedata)
        binding.homeRecyclerRomance.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerRomance.adapter = romanceAdapter

        val animationAdapter = MovieAdapter(animationdata)
        binding.homeRecyclerAnimation.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerAnimation.adapter = animationAdapter
    }


}