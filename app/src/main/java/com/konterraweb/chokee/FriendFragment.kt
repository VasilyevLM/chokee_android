package com.konterraweb.chokee

import Contact
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.konterraweb.chokee.models.ContactsViewModel
import com.konterraweb.chokee.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class FriendFragment(var contacts: ArrayList<Contact>) : Fragment() {

    private var columnCount = 1
    private lateinit var adapter: FriendRecyclerViewAdapter;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.friend_item_list, container, false)
        adapter = FriendRecyclerViewAdapter(contacts)
        // Set the adapter
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = adapter
        }

        return view
    }
}