package com.week2.Netflixclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.week2.Netflixclone.adapter.TestAdapter
import com.week2.Netflixclone.databinding.ActivityTestBinding

class TestActivity :AppCompatActivity(){

    private lateinit var testAdapter: TestAdapter
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data : ArrayList<TestData> = arrayListOf()
        data.apply{
            for(i in 0 until 30){
                add(TestData(img=R.drawable.profile1, text = i.toString(), checked = false))
            }
        }

        val testAdapter = TestAdapter(data)
        binding.testRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.testRecycler.adapter = testAdapter
    }
}