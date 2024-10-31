package com.example.connections.ui.adapters
import androidx.recyclerview.widget.DiffUtil
import com.example.connections.data.models.Contact

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        // Compare unique IDs
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        // Compare entire content
        return oldItem == newItem
    }
}