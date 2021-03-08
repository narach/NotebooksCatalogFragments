package by.itacademy.notebookscatalog.fragments.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.itacademy.notebookscatalog.fragments.database.entities.Brand
import by.itacademy.notebookscatalog.fragments.database.entities.Model

data class BrandWithModels(
    @Embedded val brand: Brand,
    @Relation(
        parentColumn = "id",
        entityColumn = "brand"
    )
    val models: List<Model>
)
