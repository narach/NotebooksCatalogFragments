package by.itacademy.notebookscatalog.fragments.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.itacademy.notebookscatalog.fragments.data.DeviceType

@Entity
data class Model(
    @PrimaryKey
    val id: Long,
    val name: String,
    val brand: Long,
    val imgUri: String,
    val deviceType: DeviceType
)
