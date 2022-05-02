package com.konterraweb.chokee.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("login")
    fun login(@Body data: HashMap<String, String>): Call<User>

    @POST("logout")
    fun logout(@Query("token") token: String): Call<ResponseBody>

    @POST("sms_request")
    fun smsRequest(@Body data: HashMap<String, String>): Call<ResponseBody>

    @POST("api/set_status")
    fun setStatus(): Call<ResponseBody>

    @POST("api/update_friends")
    fun updateFriends(): Call<ResponseBody>

    @POST("api/delete_friend")
    fun deleteFriend(): Call<ResponseBody>

    @POST("api/check_available")
    fun checkAvailable(): Call<ResponseBody>

    @POST("api/get_friends")
    fun getFriends(): Call<List<ResponseBody>>

    @POST("api/set_token")
    fun setToken(): Call<ResponseBody>
}