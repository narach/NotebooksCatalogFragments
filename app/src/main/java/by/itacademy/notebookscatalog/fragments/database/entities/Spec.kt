package by.itacademy.notebookscatalog.fragments.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Spec(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val model: Long,
    val ram: Int,
    val drive: Int,
    val proc: String,
    val color: String,
    val screen: String,
    val isDefault: Boolean
)
