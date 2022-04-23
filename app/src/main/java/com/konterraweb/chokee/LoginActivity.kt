package com.konterraweb.chokee

import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var phoneField: EditText = findViewById(R.id.phoneField)
        var loginBtn: Button = findViewById(R.id.loginBtn)
        phoneField.addTextChangedListener(PhoneNumberFormattingTextWatcher("RU"))
        loginBtn.setOnClickListener {
            var smsCodeIntent = Intent(this.applicationContext, SmsCodeActivity::class.java)
            this.startActivity(smsCodeIntent)
//            var alert: AlertDialog.Builder = AlertDialog.Builder(this)
//            alert.setMessage("Не заполнен телефон")
//            alert.setCancelable(true)
//            alert.create().show()
        }
    }
}