package com.konterraweb.chokee

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView

class SmsCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_codectivity)
        val smsBtn: Button = findViewById(R.id.smsBtn)
        val phoneText: TextView = findViewById(R.id.phoneText)
        val phone = intent.getStringExtra("phone")
        phoneText.text = "+"+PhoneNumberUtils.formatNumber(intent.getStringExtra("phone"), "+$phone", "RU")
        smsBtn.setOnClickListener {
            val mainIntent = Intent(this.applicationContext, MainActivity::class.java)
            this.startActivity(mainIntent)
        }
    }
}