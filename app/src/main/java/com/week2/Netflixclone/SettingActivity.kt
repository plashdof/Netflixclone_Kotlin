package com.week2.Netflixclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.navercorp.nid.NaverIdLoginSDK
import com.week2.Netflixclone.databinding.ActivitySettingsBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val gobackbtn = binding.btnSettingGoback

        binding.settingEmailtext.text = name
        binding.settingNametext.text = email
        binding.btnSettingLogout.setOnClickListener {
            naverLogout()
        }
        gobackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun naverLogout() {
        NaverIdLoginSDK.logout()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}