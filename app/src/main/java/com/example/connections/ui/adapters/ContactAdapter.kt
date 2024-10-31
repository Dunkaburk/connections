package com.example.connections.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connections.R
import com.example.connections.data.models.Contact

class ContactAdapter(private val onItemClick: (Contact) -> Unit) :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = getItem(position)
        holder.bind(currentContact)
    }

    class ContactViewHolder(
        itemView: View,
        private val onItemClick: (Contact) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact) {
            itemView.textViewName.text = contact.name
            itemView.textViewLastContacted.text = "Last Contacted: ${contact.lastContacted}"
            itemView.textViewFrequency.text = "Contact every ${contact.contactFrequencyDays} days"

            itemView.setOnClickListener {
                onItemClick(contact)
            }
        }
    }
}