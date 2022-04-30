package com.konterraweb.chokee.network

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceRepository {
    fun requestSMSCode(phone: String, cb: Callback<ResponseBody>) {
        val smsRequest = HashMap<String, String>();
        smsRequest["phone"] = phone;
        ApiServer.instance.service.smsRequest(smsRequest).enqueue(cb)
    }
}