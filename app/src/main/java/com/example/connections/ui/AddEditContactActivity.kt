package com.example.connections.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connections.R
import com.yourpackage.data.models.Contact
import android.view.View
import android.widget.Toast

class AddEditContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_contact)

        // Set up the UI elements and listeners

        buttonSave.setOnClickListener {
            saveContact()
        }

        // Check if we're editing an existing contact
        // TODO: Handle editing existing contact if necessary
    }

    private fun saveContact() {
        val name = editTextName.text.toString()
        val phoneNumber = editTextPhoneNumber.text.toString()
        val email = editTextEmail.text.toString()
        val frequency = editTextFrequency.text.toString().toIntOrNull()

        if (name.isBlank() || frequency == null) {
            Toast.makeText(this, "Please enter a name and contact frequency.", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, name)
            putExtra(EXTRA_PHONE, phoneNumber)
            putExtra(EXTRA_EMAIL, email)
            putExtra(EXTRA_FREQUENCY, frequency)
        }

        setResult(RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_NAME = "com.yourpackage.NAME"
        const val EXTRA_PHONE = "com.yourpackage.PHONE"
        const val EXTRA_EMAIL = "com.yourpackage.EMAIL"
        const val EXTRA_FREQUENCY = "com.yourpackage.FREQUENCY"
    }
}