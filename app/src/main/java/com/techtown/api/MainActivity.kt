package com.techtown.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "apiLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        //Stetho는 크롬으로 디버깅을 볼 수 있는 라이브러리임
        //아래 OkHttpClient에 addNetworkInterceptor(StethoInterceptor())를 해줘야 네트워크 디버깅을 볼 수 있음
        val client:OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).addInterceptor(HeaderInterceptor()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(NaverAPI::class.java)
//        val callGetSearchNews = api.getSearchNews(CLIENT_ID,  CLIENT_SECRET, "테스트")
        restbutton.setOnClickListener {
            val callGetSearchNews = api.getSearchNews("주식")
            callGetSearchNews.enqueue(object : Callback<ResultGetSearchNews> {
                override fun onResponse(
                    call: Call<ResultGetSearchNews>,
                    response: Response<ResultGetSearchNews>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "연결성공 : ${response.raw()}")
                        //response.toString()을 하고 나오는 url을 따라가면 인증실패라고 나오는데
                        //그 부분은 retrofit에서 보낸 header가 붙어서 간 url이 아닌 url이라서 인증실패라고 나옴
                        //response.body()를 통해 데이터를 갖고 올 수 있음
                        resultText.setText(response.body().toString())
                    }else{
                        Log.d(TAG, "성공 : ${response.raw()}")
                    }
                }

                override fun onFailure(call: Call<ResultGetSearchNews>, t: Throwable) {
                    Log.d(TAG, "실패 : $t")
                }
            })
        }
    }
    class HeaderInterceptor : Interceptor {
        private var clientkey = ""
        private var clientsecretkey = ""
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header("X-Naver-Client-Id", clientkey)
                    .addHeader("X-Naver-Client-Secret", clientsecretkey)
                    .build()
            )
        }

    }
}


//retrofit사용법
//1.gradle받기 https://square.github.io/retrofit/
//2.서버에서 응닫이 오는 json객체에 맞게 data class 생성
//*json객체중 필요한 정보가 일부라면,일부만 써도 가능
//변수명은 반드시 동일해야함zo
//3.인터페이스 작성
//
