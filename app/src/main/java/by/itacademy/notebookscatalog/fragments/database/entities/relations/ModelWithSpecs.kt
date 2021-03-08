package by.itacademy.notebookscatalog.fragments.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import by.itacademy.notebookscatalog.fragments.database.entities.Model
import by.itacademy.notebookscatalog.fragments.database.entities.Spec

data class ModelWithSpecs(
    @Embedded val model: Model,
    @Relation(
        parentColumn = "id",
        entityColumn = "model"
    )
    val specs: List<Spec>
)
