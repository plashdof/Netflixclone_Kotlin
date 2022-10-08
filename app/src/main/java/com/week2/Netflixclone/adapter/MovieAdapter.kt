package com.week2.Netflixclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.databinding.RecyclerMovieposterBinding

class MovieAdapter(private val datas: ArrayList<Int>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: RecyclerMovieposterBinding):RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: Int) {
            viewBinding.homeMovieBtn.setImageResource(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerMovieposterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


}