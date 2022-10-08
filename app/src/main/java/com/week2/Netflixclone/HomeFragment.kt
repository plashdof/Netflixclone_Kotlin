package com.week2.Netflixclone



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.adapter.MovieAdapter
import com.week2.Netflixclone.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment() : Fragment(){
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)


        val top10data : ArrayList<Int> = arrayListOf()
        val koreandata : ArrayList<Int> = arrayListOf()
        val thrillerdata : ArrayList<Int> = arrayListOf()
        val romancedata : ArrayList<Int> = arrayListOf()
        val animationdata : ArrayList<Int> = arrayListOf()


        top10data.apply{
            for(i in 0 until 30){
                add(R.drawable.movie1)
                add(R.drawable.movie2)
                add(R.drawable.movie3)
                add(R.drawable.movie4)
                add(R.drawable.movie5)
            }
        }

        koreandata.apply{
            for(i in 0 until 30){
                add(R.drawable.movie3)
                add(R.drawable.movie2)
                add(R.drawable.movie4)
                add(R.drawable.movie5)
                add(R.drawable.movie1)
            }
        }

        thrillerdata.apply{
            for(i in 0 until 30){
                add(R.drawable.movie5)
                add(R.drawable.movie4)
                add(R.drawable.movie3)
                add(R.drawable.movie2)
                add(R.drawable.movie1)
            }
        }

        romancedata.apply{
            for(i in 0 until 30){
                add(R.drawable.movie1)
                add(R.drawable.movie2)
                add(R.drawable.movie3)
                add(R.drawable.movie4)
                add(R.drawable.movie5)
            }
        }

        animationdata.apply{
            for(i in 0 until 30){
                add(R.drawable.movie4)
                add(R.drawable.movie3)
                add(R.drawable.movie2)
                add(R.drawable.movie1)
                add(R.drawable.movie5)
            }
        }


        binding.homeSearchBtn.setOnClickListener {
            Log.d("dddd","clicked")
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }



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



        return binding.root
    }

}