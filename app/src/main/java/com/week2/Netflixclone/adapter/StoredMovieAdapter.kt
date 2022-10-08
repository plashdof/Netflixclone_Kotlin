package com.week2.Netflixclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.databinding.RecyclerStoredmovieBinding

class StoredMovieAdapter(private val datas: ArrayList<Int>) : RecyclerView.Adapter<StoredMovieAdapter.ViewHolder>() {

    // Update
    private fun addItem(data: Int){
        datas.add(data)
        notifyDataSetChanged()
    }

    // Delete
    private fun removeItem(position: Int){
        datas.removeAt(position)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val viewBinding: RecyclerStoredmovieBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: Int) {
            viewBinding.strmovieImage.setImageResource(item)

            val addbtn = viewBinding.strmovieAddBtn
            val removebtn = viewBinding.strmovieRemoveBtn
            val position = adapterPosition

            addbtn.setOnClickListener {
                addItem(item)
            }

            removebtn.setOnClickListener {
                removeItem(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerStoredmovieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


}