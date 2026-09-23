package com.example.madd_tutorial04

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyTable(
    var name: String?,
    var dateOfBirth: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
