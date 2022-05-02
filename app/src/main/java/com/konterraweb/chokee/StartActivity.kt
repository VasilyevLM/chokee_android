package com.konterraweb.chokee

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.konterraweb.chokee.network.User
import java.lang.Exception

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref: SharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE)
        val newIntent: Intent
        var user: User? = null

        if(pref.contains("instance")) {
            try {
                val gson = Gson()
                val json = pref.getString("instance", null)
                user = gson.fromJson(json, User::class.java)
            } catch (e: Exception) {}
        }

        if(user != null) {
            (application as MyApp).user = user
            newIntent = Intent(applicationContext, MainActivity::class.java)
        } else {
            newIntent = Intent(applicationContext, LoginActivity::class.java)
        }

        startActivity(newIntent)
    }

    override fun onStart() {
        super.onStart()
        setVisible(true)
    }
}