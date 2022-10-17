package com.week2.Netflixclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.week2.Netflixclone.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SettingActivity : AppCompatActivity() {



    private lateinit var binding: ActivitySettingsBinding

    val CLIENT_ID = "XffP51OYOn4jJVGY2pPj"
    val CLIENT_SECRET = "YPea1k2aoL"
    val CLIENT_NAME = "네이버아이디 로그인"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NaverIdLoginSDK.initialize(this, CLIENT_ID, CLIENT_SECRET, CLIENT_NAME)

        getprofile()


        val gobackbtn = binding.btnSettingGoback



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

    private fun getprofile(){
        val profileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(response: NidProfileResponse) {

                binding.settingEmailtext.text = response.profile?.email
                binding.settingNametext.text = response.profile?.name
                binding.settingIdtext.text = response.profile?.id

                Log.d("결과", "${response.profile}")

            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()

                Toast.makeText(
                    this@SettingActivity,
                    "프로필 로딩 실패. 에러코드 : $errorCode",
                    Toast.LENGTH_SHORT
                )
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NidOAuthLogin().callProfileApi(profileCallback)


    }


}