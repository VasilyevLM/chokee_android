package com.konterraweb.chokee

import Contact
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.konterraweb.chokee.models.ContactsViewModel
import com.konterraweb.chokee.ui.main.SectionsPagerAdapter

class MainActivity : FragmentActivity() {
    private val viewModel by viewModels<ContactsViewModel>()
    private var contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, Array(1){Manifest.permission.READ_CONTACTS}, 100)
        } else {
            viewModel.readContacts()
        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Мой статус"
                1 -> "Мои друзья"
                2 -> "Система"
                else -> "Мой статус"
            }
        }.attach()
        viewModel.getContacts().observe(this) { items ->
            sectionsPagerAdapter.contacts = items
            sectionsPagerAdapter.updateContacts()
        }

        fab.setOnClickListener { view ->
            sectionsPagerAdapter.contacts = arrayListOf(Contact("one", "two", "tree"))
            sectionsPagerAdapter.updateContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel.readContacts()
    }

    override fun onBackPressed() {}
}