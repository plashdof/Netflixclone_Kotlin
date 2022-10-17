package com.week2.Netflixclone.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.HomeActivity
import com.week2.Netflixclone.datas.ProfileData
import com.week2.Netflixclone.databinding.RecyclerListItemBinding


class ProfileAdapter(private val datas: ArrayList<ProfileData>) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){


    inner class ViewHolder(private val viewBinding: RecyclerListItemBinding):RecyclerView.ViewHolder(viewBinding.root){
        private val context = viewBinding.root.context

        fun bind(item: ProfileData) {
            viewBinding.mainProfileBtn.setImageDrawable(item.img)
            viewBinding.mainProfileName.text = item.name
            
            // 프로필 버튼 클릭시, HomeActivity로 이동
            
            viewBinding.mainProfileBtn.setOnClickListener{

                val intent = Intent(context, HomeActivity::class.java)
                intent.run{context.startActivity(this)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val viewBinding = RecyclerListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    private fun getViewBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}