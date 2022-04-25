package com.konterraweb.chokee.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.konterraweb.chokee.AboutFragment
import com.konterraweb.chokee.FriendFragment

class SectionsPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment = when(position) {
            0 -> PlaceholderFragment()
            1 -> FriendFragment()
            2 -> AboutFragment()
        else -> PlaceholderFragment()
    }
}