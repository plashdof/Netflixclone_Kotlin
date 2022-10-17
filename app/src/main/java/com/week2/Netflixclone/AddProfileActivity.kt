package com.week2.Netflixclone

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.week2.Netflixclone.databinding.ActivityAddprofileBinding
import org.json.JSONArray
import java.io.ByteArrayOutputStream

class AddProfileActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var profilebitmap: Bitmap
    private lateinit var binding: ActivityAddprofileBinding
    var profilename: String = ""
    var profilenamearr: ArrayList<String> = ArrayList()
    var profileimgarr: ArrayList<String> = ArrayList()
    var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gobackBtn = binding.addprofileGobackBtn
        val storeBtn = binding.addprofileStoreBtn
        val changeprofileBtn = binding.addprofileImageBtn


        storeBtn.isEnabled = false
        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)


        // 뒤로가기버튼 클릭 리스너
        gobackBtn.setOnClickListener {
            movebackPage()
        }

        // 저장버튼 클릭 리스너
        storeBtn.setOnClickListener {
            makeProfile()
        }

        changeprofileBtn.setOnClickListener {
            movechangePage()
        }

    }


    override fun onStart() {
        super.onStart()
        Log.d("aa", "onStart")

        val editText = binding.addprofileInput
        val storeBtn = binding.addprofileStoreBtn


        // local 에 저장된 String 형태의 profile 이름 배열을 불러오기.
        // JSONArray -> ArrayList 로 형태변환
        val getSharedName = sharedPreferences.getString("profilenamearr", "ERROR")
        val getSharedImage = sharedPreferences.getString("profileimgarr", "ERROR")

        if (getSharedName != "ERROR") {
            val arrJson = JSONArray(getSharedName)
            for (i in 0 until arrJson.length()) {
                profilenamearr.add(arrJson.optString(i))
            }
        }

        if (getSharedImage != "ERROR") {
            val arrJsonimg = JSONArray(getSharedImage)
            for (i in 0 until arrJsonimg.length()) {
                profileimgarr.add(arrJsonimg.optString(i))
            }
        }


        // EditText에 글자 입력시, 저장버튼 활성화(+색바뀜).
        // 바뀌는 Text값을 profilename String 변수에 저장

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                profilename = editText.text.toString()

                // text 입력되어있을때만 버튼 활성화
                storeBtn.isEnabled = profilename.isNotBlank()


                // text 입력되어있을때 색 : Red
                // text 입력 안되어있을때 색 : DarkRed
                if (profilename.isNotBlank()) {
                    storeBtn.setTextColor(Color.RED)
                } else {
                    storeBtn.setTextColor(Color.rgb(59, 11, 23))
                }

            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    override fun onResume() {
        super.onResume()

        val profileimg = binding.addprofileImageBtn

        // 투명창인 프로필 사진 변경 창 끝났을경우, 해당 선택한 프로필로 변경해준다!
        // Glide 라이브러리 사용

        if (intent.getStringExtra("changeprofile") == "success") {

            val bit = intent.getParcelableExtra<Bitmap>("profileimg")

            // AddChangeProfileActivity -> AddProfileActivity 일경우, 애니메이션 제거
            overridePendingTransition(R.anim.none, R.anim.none)

            // Bitmap 을 Drawable 로 변경
            val d = BitmapDrawable(resources, bit)
            Glide.with(this)
                .load(d)
                .into(profileimg)
        }
    }

    // 프로필 추가 성공시 Toast 메세지
    // 뒤로가기 눌렀을때 Toast 메세지
    override fun onStop() {
        super.onStop()
        if (flag) {
            Toast.makeText(this@AddProfileActivity, "프로필을 추가했습니다", Toast.LENGTH_SHORT).show()
        }

    }


    // 저장버튼 클릭시 호출 메소드
    // profilenamearr 에 새로운 profile 이름 추가한뒤, String으로 변환하여 Local 에 저장.
    private fun makeProfile() {

        // 선택한 프로필이미지를 bitmap으로 변환
        val profileimg = binding.addprofileImageBtn
        profilebitmap = getViewBitmap(profileimg)

        // profile 이름 배열에 새로 입력한 이름 추가
        // profile 이미지 배열에, 선택한 프로필이미지 Bitmap -> String으로 변환해서 추가
        profilenamearr.add(profilename)
        profileimgarr.add(BitmapToString(profilebitmap))
        flag = true

        // local 에 저장하기 위해 다시 String으로 형변환
        val jsonArr = JSONArray()
        for (i in profilenamearr) {
            jsonArr.put(i)
        }
        val result = jsonArr.toString()

        val jsonArrImg = JSONArray()
        for (i in profileimgarr) {
            jsonArrImg.put(i)
        }
        val resultimg = jsonArrImg.toString()

        // local 에 갱신된 profile 배열 String 형태로 저장
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("profilenamearr", result)
        editor.apply()
        editor.putString("profileimgarr", resultimg)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    // 뒤로가기 버튼 클릭시 호출 메소드
    // 뒤로가기 버튼 클릭시 MainActivity로 화면이동
    private fun movebackPage() {
        val intent = Intent(this, MainActivity::class.java)
            .putExtra("addprofile", "fail")
        startActivity(intent)
    }

    private fun movechangePage() {
        val intent = Intent(this, AddChangeProfileActivity::class.java)
        startActivity(intent)
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


    // SharedPreferences 에 저장하기 위해, Bitmap을 String으로 변환해주는 메소드

    fun BitmapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream() //바이트 배열을 차례대로 읽어 들이기위한 ByteArrayOutputStream클래스 선언
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos) //bitmap을 압축 (숫자 70은 70%로 압축한다는 뜻)
        val bytes = baos.toByteArray() //해당 bitmap을 byte배열로 바꿔준다.
        return Base64.encodeToString(bytes, Base64.DEFAULT) //Base 64 방식으로byte 배열을 String으로 변환
        //String을 retrurn
    }


}