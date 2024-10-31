// Contact.kt
package com.yourpackage.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var phoneNumber: String? = null,
    var email: String? = null,
    var lastContacted: LocalDate? = null,
    var contactFrequencyDays: Int = 30
)