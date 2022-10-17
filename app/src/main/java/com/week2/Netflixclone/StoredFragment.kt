package com.week2.Netflixclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.adapter.StoredMovieAdapter
import com.week2.Netflixclone.databinding.FragmentStoredBinding

class StoredFragment : Fragment() {
    private lateinit var binding: FragmentStoredBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoredBinding.inflate(layoutInflater)

        val storedmovie: ArrayList<Int> = arrayListOf()


        storedmovie.apply {
            add(R.drawable.movie1)
            add(R.drawable.movie2)
            add(R.drawable.movie3)
            add(R.drawable.movie4)
            add(R.drawable.movie5)
        }

        val storedAdapter = StoredMovieAdapter(storedmovie)
        binding.storedRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.storedRecycler.adapter = storedAdapter


        return binding.root
    }
}