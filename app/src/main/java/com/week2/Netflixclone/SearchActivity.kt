package com.week2.Netflixclone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Netflixclone.adapter.SearchAdapter
import com.week2.Netflixclone.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity(){

    val CLIENT_ID = "le7B3SvvYC8DIrustMLq"
    val CLIENT_SECRET = "h8zw5x6YUr"
    val BASE_URL_NAVER_API = "https://openapi.naver.com"


    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding : ActivitySearchBinding
    private lateinit var movieList: ArrayList<String>
    var searchtext : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edittext = binding.searchEdittext
        val searchbtn = binding.searchBtn
        
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
            if(edittext.text.isEmpty()){
                return@setOnClickListener
            }
            getMovieAPI()
        }

    }

    private fun getMovieAPI(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
        val callMovieData = api.getMoviePoster(CLIENT_ID, CLIENT_SECRET, searchtext)

        callMovieData.enqueue(object : Callback<MovieData> {
            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>
            ) {
                movieList = arrayListOf(
                    response.body()!!.image
                )
                Log.d("결과", "${response.body()}")
                Log.d("결과", "${response.raw()}")

                recycler()

            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("결과:", "실패 : $t")
            }
        })
    }

    private fun recycler(){
        // 어뎁터 연결 &레이아웃 매니저 호출 & 레이아웃 설정
        val adapter = SearchAdapter(movieList)
        binding.searchRecycler.layoutManager = GridLayoutManager(this@SearchActivity, 4)
        binding.searchRecycler.adapter = adapter
    }

}