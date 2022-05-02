package com.konterraweb.chokee

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.google.gson.Gson
import com.konterraweb.chokee.network.ApiServer
import com.konterraweb.chokee.network.ServiceRepository
import com.konterraweb.chokee.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SmsCodeActivity : AppCompatActivity() {
    lateinit var smsField: SmsConfirmationView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_codectivity)
        val smsBtn: Button = findViewById(R.id.smsBtn)
        val phoneText: TextView = findViewById(R.id.phoneText)
        smsField = findViewById(R.id.sms_field)
        val phone = intent.getStringExtra("phone")
        val formattedPhone = PhoneNumberUtils.formatNumber(phone, "+$phone", "RU")
        phoneText.text = "+$formattedPhone"
        smsBtn.setOnClickListener(sendSms)
    }

    val sendSms = View.OnClickListener {
        val phone = intent.getStringExtra("phone") as String
        if(phone != null) {
            ServiceRepository()
                .login(phone, smsField.enteredCode, object : Callback<User> {

                    @SuppressLint("ApplySharedPref")
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful) {
                            val user: User? = response.body()
                            if(user != null) {
                                val json: String = Gson().toJson(user, User::class.java)
                                val prefs: SharedPreferences.Editor = applicationContext
                                    .getSharedPreferences("User", Context.MODE_PRIVATE)
                                    .edit();
                                prefs.putString("instance", json)
                                prefs.commit()
                                (application as MyApp).user = user
                                val mainIntent =
                                    Intent(applicationContext, MainActivity::class.java)
                                startActivity(mainIntent)
                            }
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(applicationContext, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            Toast.makeText(applicationContext, "Вернитесь на шаг назад и введите номер телефона", Toast.LENGTH_LONG).show()
        }
    }
}