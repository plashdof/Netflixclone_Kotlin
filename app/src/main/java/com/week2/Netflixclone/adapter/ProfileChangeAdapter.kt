package com.week2.Netflixclone.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.databinding.RecyclerDeleteItemBinding
import kotlinx.android.synthetic.main.recycler_delete_item.view.*

class ProfileChangeAdapter(private val datas : ArrayList<Int>) : RecyclerView.Adapter<ProfileChangeAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener


    inner class ViewHolder(private val viewBinding: RecyclerDeleteItemBinding):RecyclerView.ViewHolder(viewBinding.root){


        fun bind(item: Int){
            viewBinding.profilechangeDeleteBtn.setImageResource(item)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileChangeAdapter.ViewHolder {
        val viewBinding = RecyclerDeleteItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ProfileChangeAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.profilechange_delete_btn.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }


    // Click event 를, Activity에서 처리하기 위한 함수들

    interface OnItemClickListener{
        fun onClick(v:View, position:Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = datas.size



}