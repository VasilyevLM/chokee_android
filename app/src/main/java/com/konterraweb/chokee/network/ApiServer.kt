package com.konterraweb.chokee.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServer() {
    private val ENTRY_URL = "http://chokee.konterraweb.ru/"
    lateinit var service: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ENTRY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    companion object {
        val instance = ApiServer()
    }
}