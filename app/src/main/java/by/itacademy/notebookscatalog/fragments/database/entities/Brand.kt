package by.itacademy.notebookscatalog.fragments.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    @PrimaryKey
    val id: Long,
    val name: String,
    val headquartersId: Long
)
