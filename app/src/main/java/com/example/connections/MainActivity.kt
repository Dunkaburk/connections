package com.example.connections

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connections.ui.AddEditContactActivity
import com.example.connections.ui.adapters.ContactAdapter
import com.example.connections.viewmodel.ContactViewModel
import com.yourpackage.data.models.Contact
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity
        setContentView(R.layout.activity_main)

        // Initialize the RecyclerView and Adapter
        adapter = ContactAdapter { contact: Contact ->
            // Handle contact item click (for editing or viewing details)
            // TODO: Implement item click functionality
        }

        recyclerViewContacts.adapter = adapter
        recyclerViewContacts.layoutManager = LinearLayoutManager(this)

        // Initialize the ViewModel
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        // Observe the LiveData from ViewModel
        contactViewModel.allContacts.observe(this, Observer { contacts ->
            contacts?.let {
                adapter.submitList(it)
            }
        })

        // Handle the FAB click to add a new contact
        fabAddContact.setOnClickListener {
            val intent = Intent(this, AddEditContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT_REQUEST)
        }
    }

    companion object {
        const val ADD_CONTACT_REQUEST = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_CONTACT_REQUEST && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra(AddEditContactActivity.EXTRA_NAME) ?: ""
            val phone = data?.getStringExtra(AddEditContactActivity.EXTRA_PHONE)
            val email = data?.getStringExtra(AddEditContactActivity.EXTRA_EMAIL)
            val frequency = data?.getIntExtra(AddEditContactActivity.EXTRA_FREQUENCY, 30)

            val newContact = Contact(
                name = name,
                phoneNumber = phone,
                email = email,
                contactFrequencyDays = frequency ?: 30,
                lastContacted = LocalDate.now()
            )

            contactViewModel.insert(newContact)
            Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Contact not saved", Toast.LENGTH_SHORT).show()
        }
    }
}