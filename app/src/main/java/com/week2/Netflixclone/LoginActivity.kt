package com.week2.Netflixclone


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import com.week2.Netflixclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val CLIENT_ID = "XffP51OYOn4jJVGY2pPj"
    val CLIENT_SECRET = "YPea1k2aoL"
    val CLIENT_NAME = "네이버아이디 로그인"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginbtn = binding.btnLogin
        
        // 네아로 객체 초기화
        NaverIdLoginSDK.initialize(this, CLIENT_ID, CLIENT_SECRET, CLIENT_NAME)

        loginbtn.setOnClickListener {
            startLogin()
        }

    }

    private fun startLogin() {
        var naverToken: String? = ""
        var username: String? = ""
        var useremail: String? = ""
        val profileCallback = object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(response: NidProfileResponse) {
                username = response.profile?.name
                useremail = response.profile?.email

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    .putExtra("username", username)
                    .putExtra("useremail", useremail)
                startActivity(intent)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()

                Toast.makeText(
                    this@LoginActivity,
                    "프로필 로딩 실패. 에러코드 : $errorCode",
                    Toast.LENGTH_SHORT
                )
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }


        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {

                // accesstoken 받아오기
                naverToken = NaverIdLoginSDK.getAccessToken()

//                var naverRefreshToken = NaverIdLoginSDK.getRefreshToken()
//                var naverExpiresAt = NaverIdLoginSDK.getExpiresAt().toString()
//                var naverTokenType = NaverIdLoginSDK.getTokenType()
//                var naverState = NaverIdLoginSDK.getState().toString()

                //로그인 유저 정보 가져오기
                NidOAuthLogin().callProfileApi(profileCallback)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()

                Toast.makeText(this@LoginActivity, "로그인 실패. 에러코드 : $errorCode", Toast.LENGTH_SHORT)
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }
}


