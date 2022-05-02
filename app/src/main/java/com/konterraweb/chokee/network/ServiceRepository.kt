package com.konterraweb.chokee.network

import okhttp3.ResponseBody
import retrofit2.Callback

class ServiceRepository {
    fun requestSMSCode(phone: String, cb: Callback<ResponseBody>) {
        val smsRequest = HashMap<String, String>();
        smsRequest["phone"] = phone;
        ApiServer.instance.service.smsRequest(smsRequest).enqueue(cb)
    }

    fun login(phone: String, code: String, cb: Callback<User>) {
        val loginRequest = HashMap<String, String>()
        loginRequest["phone"] = phone
        loginRequest["code"] = code

        ApiServer.instance.service.login(loginRequest).enqueue(cb)
    }

    fun logout(user: User, cb: Callback<ResponseBody>) {
        ApiServer.instance.service.logout(user.token).enqueue(cb)
    }
}