package com.konterraweb.chokee

import Contact
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.konterraweb.chokee.placeholder.PlaceholderContent.PlaceholderItem
import com.konterraweb.chokee.databinding.FriendItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class FriendRecyclerViewAdapter(
    var values: ArrayList<Contact>
) : RecyclerView.Adapter<FriendRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FriendItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.userName
        holder.contentView.text = item.phone
//        val imageRes = holder.img.context.resources.getIdentifier(
//            "@drawable/i19",
//            null,
//            holder.img.context.packageName
//        )
//        holder.img.setImageResource(imageRes)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FriendItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.userName
        val contentView: TextView = binding.statusName
        val img: ImageView = binding.statusImage
    }

}