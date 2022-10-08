package com.week2.Netflixclone.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Netflixclone.TestData
import com.week2.Netflixclone.databinding.RecyclerTestBinding
import kotlinx.android.synthetic.main.recycler_test.view.*

class TestAdapter(private val datas: ArrayList<TestData>) : RecyclerView.Adapter<TestAdapter.ViewHolder>(){

    // check 표시여부 저장할 데이터사이즈와 동일한 크기의 배열 생성.
    var checkarr : Array<Boolean> = Array(datas.size){false}


    inner class ViewHolder(private val viewBinding: RecyclerTestBinding):RecyclerView.ViewHolder(viewBinding.root){

        private val context = viewBinding.root.context



        fun bind(item: TestData) {
            Log.d("testrec", "bind")

            val checked = viewBinding.testCheckbox.isChecked
            viewBinding.testImage.setImageResource(item.img)
            viewBinding.testText.text = item.text

            val position = adapterPosition

            // 조건문 1 )
            // 이미 체크되어있는것을 재사용하는것인지 checked 변수를 통하여 확인
            // 체크표시를 띄울지 결정하는 checkarr[position]을 통하여 확인

            if(!checkarr[position] && checked){
                viewBinding.testCheckbox.isChecked = false
            }

            // 조건문 2 )
            // checkarr[position] 이라면 체크표시한 상태로 출력

            if(checkarr[position]){
                viewBinding.testCheckbox.isChecked = true
            }


            viewBinding.testCheckbox.setOnClickListener{

                // 조건문 3 )
                // 클릭시, checkarr[position] 값 변경

                when(checked){
                    true -> checkarr[position] = false
                    false -> checkarr[position] = true
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        Log.d("testrec", "onCreateViewHolder")
        val viewBinding = RecyclerTestBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        Log.d("testrec","onBindViewHolder : position = $position")
        holder.bind(datas[position])
    }


    override fun getItemCount(): Int{
        return datas.size
    }


}