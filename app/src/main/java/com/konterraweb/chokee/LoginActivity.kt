package com.konterraweb.chokee

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.konterraweb.chokee.network.ApiServer
import com.konterraweb.chokee.network.ServiceRepository
import com.konterraweb.chokee.network.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : Activity() {
    lateinit var phoneField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener(loginClick)
        phoneField = findViewById(R.id.phoneField)
        phoneField.addTextChangedListener(PhoneNumberFormattingTextWatcher("RU"))
    }

    private val loginClick = View.OnClickListener {
        var smsCodeIntent = Intent(this.applicationContext, SmsCodeActivity::class.java)
        val phone = "7"+phoneField.text.toString().replace(Regex("[^0-9]"), "")

        if(phone.length != 11) {
            Toast.makeText(this.applicationContext, "Телефон не заполнен", Toast.LENGTH_SHORT).show()
        } else {
            val smsRequest = HashMap<String, String>();
            smsRequest.set("phone", phone);
            ServiceRepository().requestSMSCode(phone, object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val json: JSONObject? = response.body()?.string()?.let { it1 -> JSONObject(it1) }
                    if(response.isSuccessful && json?.get("status") == "success") {
                        smsCodeIntent.putExtra("phone", phone)
                        startActivity(smsCodeIntent)
                    } else {
                        Toast.makeText(applicationContext, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println(t)
                    Toast.makeText(applicationContext, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onBackPressed() {}
}