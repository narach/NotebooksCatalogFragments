package by.itacademy.notebookscatalog.fragments.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    @PrimaryKey
    val id: Long,
    val code: String,
    val name: String
) {
    constructor(code: String, name: String) : this(0, code, name)
}