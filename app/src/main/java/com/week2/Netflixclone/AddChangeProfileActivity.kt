package com.week2.Netflixclone

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.week2.Netflixclone.databinding.ActivityAddchangeprofileBinding

class AddChangeProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddchangeprofileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddchangeprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        overridePendingTransition(R.anim.none, R.anim.none)

        val profile1 = binding.changeProfile1
        val profile2 = binding.changeProfile2
        val profile3 = binding.changeProfile3
        val profile4 = binding.changeProfile4
        val profile5 = binding.changeProfile5

        profile1.setOnClickListener {
            val bit = getViewBitmap(profile1)
            movetoAddProfile(bit)
        }
        profile2.setOnClickListener {
            val bit = getViewBitmap(profile2)
            movetoAddProfile(bit)
        }
        profile3.setOnClickListener {
            val bit = getViewBitmap(profile3)
            movetoAddProfile(bit)
        }
        profile4.setOnClickListener {
            val bit = getViewBitmap(profile4)
            movetoAddProfile(bit)
        }
        profile5.setOnClickListener {
            val bit = getViewBitmap(profile5)
            movetoAddProfile(bit)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(R.anim.none, R.anim.none)
    }

    // 뷰를 Bitmap 으로 변환해주는 메소드

    private fun getViewBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun movetoAddProfile(bitmap: Bitmap) {
        val intent = Intent(this@AddChangeProfileActivity, AddProfileActivity::class.java)
            .putExtra("profileimg", bitmap)
            .putExtra("changeprofile", "success")
        startActivity(intent)
    }


}