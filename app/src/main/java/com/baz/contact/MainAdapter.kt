package com.baz.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : ListAdapter<Contact, MainAdapter.ViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_default, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(contact: Contact) {
            view.findViewById<TextView>(R.id.contactSelectionListItemName).text = contact.name
            view.findViewById<TextView>(R.id.contactSelectionListItemPhone).text = contact.number
        }
    }

    class Diff : DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = (oldItem == newItem)

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = (oldItem.id == newItem.id)

    }
}