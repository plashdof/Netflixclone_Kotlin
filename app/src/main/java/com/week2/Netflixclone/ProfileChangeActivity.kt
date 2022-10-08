package com.week2.Netflixclone

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Netflixclone.adapter.ProfileChangeAdapter
import com.week2.Netflixclone.databinding.ActivityProfilechangeBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profilechange.*
import kotlinx.android.synthetic.main.recycler_delete_item.*
import org.json.JSONArray

class ProfileChangeActivity : AppCompatActivity(){

    private lateinit var sharedpreferences : SharedPreferences
    private lateinit var binding : ActivityProfilechangeBinding
    private lateinit var profilechangeadapter: ProfileChangeAdapter
    private val data = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilechangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreferences = getSharedPreferences("test", MODE_PRIVATE)

        overridePendingTransition(R.anim.none, R.anim.none)

        loadprofilechange()

        val gomainBtn = binding.profilechangeGobackBtn

        // 뒤로가기 버튼 눌렀을때 페이지 이동
        gomainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

        // 휴지통 삭제버튼 눌렀을때, local 저장된 해당 포지션의 프로필데이터 삭제후, 페이지 이동
        profilechangeadapter.setItemClickListener(object: ProfileChangeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int){
                Log.d("aa", "Clicked")
                deleteprofile(position)
                val intent = Intent(this@ProfileChangeActivity, MainActivity::class.java)
                intent.putExtra("result", "success")
                startActivity(intent)
            }
        })

    }


    // 뒤로가기 버튼 애니메이션 재정의
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.none, R.anim.none)
    }

    
    // local 에서 데이터 불러와서 파싱하는 메소드
    private fun loadprofilechange(){
        val getSharedname = sharedpreferences.getString("profilenamearr", "ERROR")
        val profilenamearr : ArrayList<String> = ArrayList()

        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행

        if(getSharedname != "ERROR") {
            val arrJson = JSONArray(getSharedname)
            for (i in 0 until arrJson.length()) {
                profilenamearr.add(arrJson.optString(i))
            }
            recycler(profilenamearr.size)
        }

    }
    
    
    // local 데이터 파싱 끝나면, recyclerview 화면에 띄우기
    private fun recycler(size :Int){


        Log.d("aa", "$size")

        data.apply{

            for(i in 0 until size){
                add(R.drawable.main_delete)
            }

            profilechangeadapter = ProfileChangeAdapter(data)
            binding.profilechangeRecycler.layoutManager = GridLayoutManager(this@ProfileChangeActivity, 2)
            binding.profilechangeRecycler.adapter = profilechangeadapter
        }


    }


    // 프로필데이터 삭제하는 과정.
    
    // local 데이터 불러와서 배열로 데이터파싱
    // -> 해당 포지션의 데이터 삭제
    // -> 다시 String으로 데이터 파싱후 local 에 덮어쓰기
    private fun deleteprofile(position: Int){
        val getSharedname = sharedpreferences.getString("profilenamearr", "ERROR")
        val getSharedimg = sharedpreferences.getString("profileimgarr" , "ERROR")
        val profilenamearr : ArrayList<String> = ArrayList()
        val profileimgarr : ArrayList<String> = ArrayList()

        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행
        
        //프로필 이름
        if(getSharedname != "ERROR"){
            val arrJson = JSONArray(getSharedname)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
            profilenamearr.removeAt(position)
        }

        // 프로필 사진
        if(getSharedimg != "ERROR"){
            val arrJsonimg = JSONArray(getSharedimg)
            for(i in 0 until arrJsonimg.length()){
                profileimgarr.add(arrJsonimg.optString(i))
            }
            profileimgarr.removeAt(position)
        }

        
        // local 에 저장하기 위해 다시 String으로 형변환
        // 프로필 이름
        val jsonArr = JSONArray()
        for(i in profilenamearr){
            jsonArr.put(i)
        }
        val resultname = jsonArr.toString()
        
        
        // 프로필 사진
        val jsonArrimg = JSONArray()
        for(i in profileimgarr){
            jsonArrimg.put(i)
        }
        val resultimg = jsonArrimg.toString()

        // local 에 갱신된 profile 배열 String 형태로 저장
        val editor: SharedPreferences.Editor = sharedpreferences.edit()
        editor.putString("profilenamearr", resultname)
        editor.apply()
        editor.putString("profileimgarr", resultimg)
        editor.apply()
    }

    




}
