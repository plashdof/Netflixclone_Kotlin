package com.week2.Netflixclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.week2.Netflixclone.databinding.RecyclerMovieposterBinding

class SearchAdapter(private val datas: ArrayList<String>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {



    inner class ViewHolder(private val viewBinding: RecyclerMovieposterBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: String) {
            Glide.with(itemView)
                .load(item)
                .into(viewBinding.homeMovieBtn)

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