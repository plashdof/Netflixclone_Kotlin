package com.week2.Netflixclone



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.Retromovie.buildRetro
import com.week2.Netflixclone.Retromovie.startApi
import com.week2.Netflixclone.adapter.MovieAdapter
import com.week2.Netflixclone.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment() : Fragment(){
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentHomeBinding

    val top10data : ArrayList<String> = arrayListOf()
    val koreandata : ArrayList<String> = arrayListOf()
    val thrillerdata : ArrayList<String> = arrayListOf()
    val romancedata : ArrayList<String> = arrayListOf()
    val animationdata : ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)


        buildRetro
        startApi.getMoviePoster(Retromovie.CLIENT_ID, Retromovie.CLIENT_SECRET, "혹성", 100, 1)
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(
                    call: Call<MovieData>,
                    response: Response<MovieData>
                ) {
                    Log.d("결과", "${response.body()!!.items}")
                    val size = response.body()!!.items.size

                    if(size != 0){
                        for(i in 0 until size){
                            if(response.body()!!.items[i].image != ""){
                                top10data.add(response.body()!!.items[i].image)
                                koreandata.add(response.body()!!.items[i].image)
                                thrillerdata.add(response.body()!!.items[i].image)
                                romancedata.add(response.body()!!.items[i].image)
                                animationdata.add(response.body()!!.items[i].image)
                            }
                        }
                    }

                    recycler()

                }
                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.d("결과:", "실패 : $t")
                }
            })



        binding.homeSearchBtn.setOnClickListener {
            Log.d("dddd","clicked")
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    private fun recycler(){
        val top10Adapter = MovieAdapter(top10data)
        binding.homeRecyclerTop10.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerTop10.adapter = top10Adapter

        val koreanAdapter = MovieAdapter(koreandata)
        binding.homeRecyclerKorean.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerKorean.adapter = koreanAdapter

        val thrillerAdapter = MovieAdapter(thrillerdata)
        binding.homeRecyclerThriller.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerThriller.adapter = thrillerAdapter

        val romanceAdapter = MovieAdapter(romancedata)
        binding.homeRecyclerRomance.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerRomance.adapter = romanceAdapter

        val animationAdapter = MovieAdapter(animationdata)
        binding.homeRecyclerAnimation.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerAnimation.adapter = animationAdapter
    }

}