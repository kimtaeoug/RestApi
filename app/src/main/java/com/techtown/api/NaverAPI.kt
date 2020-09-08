package com.techtown.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

//interface NaverAPI {
//    @Headers("X-Naver-Client-Id: tt2Sx2Jl7Uu4AlDK4DhT", "X-Naver-Client-Secret: Tv4QSIJCCi")
//    @GET("v2/search/web")
//    fun getSearchNews(
////        @Header("X-Naver-Client-Id") clientId: String,
////        @Header("X-Naver-Client-Secret") clientSecret: String,
//        @Query("query") query: String,
//        @Query("display") display: Int? = null,
//        @Query("start") start: Int? = null
//    ): Call<ResultGetSearchNews>
//}
interface NaverAPI {
//    @Headers("Authorization: KakaoAK bb99cb237bbe653b48468280ebda6d7b")
    @GET("v1/search/news.json")
    fun getSearchNews(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<ResultGetSearchNews>
}