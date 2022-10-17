package com.week2.Netflixclone.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.databinding.RecyclerNewhotBinding

class NewhotAdapter(private val datas: ArrayList<String>) : RecyclerView.Adapter<NewhotAdapter.ViewHolder>(){


    inner class ViewHolder(private val viewBinding: RecyclerNewhotBinding):RecyclerView.ViewHolder(viewBinding.root){
        private val context = viewBinding.root.context

        fun bind(item : String){
            val Webview = viewBinding.newhotRecyclerVideos
            Webview.settings.apply{
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportMultipleWindows(true)
            }
            Webview.apply{
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                loadUrl(item)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = RecyclerNewhotBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size



}