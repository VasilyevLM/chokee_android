package com.konterraweb.chokee

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.konterraweb.chokee.network.ApiServer
import com.konterraweb.chokee.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, this.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Мой статус"
                1 -> "Мои друзья"
                2 -> "Система"
                else -> "Мой статус"
            }
        }.attach()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onBackPressed() {}
}