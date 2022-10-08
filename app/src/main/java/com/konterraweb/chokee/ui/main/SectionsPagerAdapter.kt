package com.konterraweb.chokee.ui.main

import Contact
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.konterraweb.chokee.AboutFragment
import com.konterraweb.chokee.FriendFragment
import com.konterraweb.chokee.FriendRecyclerViewAdapter

class SectionsPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    lateinit var contacts: ArrayList<Contact>
    var friendFragment: FriendFragment? = null
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PlaceholderFragment()
            1 -> {
                friendFragment = FriendFragment(contacts)
                return friendFragment as FriendFragment
            }
            2 -> AboutFragment()
            else -> PlaceholderFragment()
        }
    }

    fun updateContacts()
    {
        if(friendFragment != null) {
            val adapter: FriendRecyclerViewAdapter = (friendFragment.view as RecyclerView).adapter as FriendRecyclerViewAdapter
            friendFragment!.contacts = contacts
            adapter.values = contacts
            (friendFragment!.view as RecyclerView).adapter?.notifyDataSetChanged()
        }
    }
}