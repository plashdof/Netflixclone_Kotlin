package com.week2.Netflixclone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Netflixclone.api.Retromovie.CLIENT_ID
import com.week2.Netflixclone.api.Retromovie.CLIENT_SECRET
import com.week2.Netflixclone.adapter.SearchAdapter
import com.week2.Netflixclone.api.Retromovie
import com.week2.Netflixclone.databinding.ActivitySearchBinding
import com.week2.Netflixclone.datas.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {


    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: ActivitySearchBinding
    private lateinit var movieList: ArrayList<String>
    var searchtext: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edittext = binding.searchEdittext
        val searchbtn = binding.searchBtn
        val gobackbtn = binding.btnSearchGoback

        // 검색어 String 변수에 저장
        edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchtext = edittext.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        // 검색버튼 클릭시
        searchbtn.setOnClickListener {
            if (edittext.text.isEmpty()) {
                return@setOnClickListener
            }
            getMovieAPI()
        }

        gobackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getMovieAPI() {

        Retromovie.api.getMoviePoster(CLIENT_ID, CLIENT_SECRET, searchtext, 100, 1)
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(
                    call: Call<MovieData>,
                    response: Response<MovieData>
                ) {
                    Log.d("결과", "${response.body()!!.items}")
                    val size = response.body()!!.items.size
                    movieList = arrayListOf()

                    if (size != 0) {
                        for (i in 0 until size) {
                            if (response.body()!!.items[i].image != "") {
                                movieList.add(response.body()!!.items[i].image)
                            }
                        }
                    }

                    recycler()

                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.d("결과:", "실패 : $t")
                }
            })
    }

    private fun recycler() {
        // 어뎁터 연결 &레이아웃 매니저 호출 & 레이아웃 설정
        val adapter = SearchAdapter(movieList)
        binding.searchRecycler.layoutManager = GridLayoutManager(this@SearchActivity, 4)
        binding.searchRecycler.adapter = adapter
    }

}