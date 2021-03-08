package by.itacademy.notebookscatalog.fragments.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.itacademy.notebookscatalog.fragments.database.entities.Brand
import by.itacademy.notebookscatalog.fragments.database.entities.Country

data class BrandAndHeadquarters(
    @Embedded val brand: Brand,
    @Relation(
        parentColumn = "headquartersId",
        entityColumn = "id"
    )
    val headquarter: Country
)
