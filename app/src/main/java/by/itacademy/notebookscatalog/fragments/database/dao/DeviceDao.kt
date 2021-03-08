package by.itacademy.notebookscatalog.fragments.database.dao

import androidx.room.*
import by.itacademy.notebookscatalog.fragments.database.entities.Brand
import by.itacademy.notebookscatalog.fragments.database.entities.Country
import by.itacademy.notebookscatalog.fragments.database.entities.Model
import by.itacademy.notebookscatalog.fragments.database.entities.Spec
import by.itacademy.notebookscatalog.fragments.database.entities.relations.BrandAndHeadquarters
import by.itacademy.notebookscatalog.fragments.database.entities.relations.BrandWithModels
import by.itacademy.notebookscatalog.fragments.database.entities.relations.ModelWithSpecs

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrand(brand: Brand)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModel(model: Model)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpec(spec: Spec)

    @Transaction
    @Query("SELECT * FROM brand WHERE name = :brandName")
    suspend fun getBrandAndCountryWithBrandName(brandName: String) : List<BrandAndHeadquarters>

    @Transaction
    @Query("SELECT * FROM brand WHERE name = :brandName")
    suspend fun getBrandWithModelsByBrandName(brandName: String) : List<BrandWithModels>

    @Transaction
    @Query("SELECT * FROM model WHERE name = :modelName")
//    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    suspend fun getModelWithSpecsByModelName(modelName: String) : List<ModelWithSpecs>
}