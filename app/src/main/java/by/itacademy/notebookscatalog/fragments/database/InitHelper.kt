package by.itacademy.notebookscatalog.fragments.database

import android.content.Context
import by.itacademy.notebookscatalog.fragments.data.DeviceType
import by.itacademy.notebookscatalog.fragments.database.entities.Brand
import by.itacademy.notebookscatalog.fragments.database.entities.Country
import by.itacademy.notebookscatalog.fragments.database.entities.Model
import by.itacademy.notebookscatalog.fragments.database.entities.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object InitHelper {
    suspend fun createDb(context: Context) {
        val dao = DeviceDatabase.getInstance(context).deviceDao
        val countries = listOf(
            Country(1L, "us", "USA"),
            Country(2L, "ch", "China"),
            Country(3L, "kr", "South Korea")
        )
        val brands = listOf(
            Brand(1L, "Apple", 1L),
            Brand(2L, "Dell", 1L),
            Brand(3L, "Huawei", 2L),
            Brand(4L, "Xiaomi", 2L),
            Brand(5L, "Asus", 3L),
            Brand(6L, "HP", 3L),
        )
        val models = listOf(
            Model(1L, "Macbook Air 13 M1 2020", 1L,
                "https://content2.onliner.by/catalog/device/header/9a08e5f5400e508fb975bf697669c2b5.jpeg", DeviceType.NOTEBOOK),
            Model(2L, "MacBook Pro 16 2019", 1L,
                "https://content2.onliner.by/catalog/device/header/db499f52fa0d4b9c31fd30231359b4c1.jpeg", DeviceType.NOTEBOOK),
            Model(3L, "Inspiron 14 7400-8532", 2L,
                "https://content2.onliner.by/catalog/device/header/77e225a62a0ade5fedd9262517ea779b.jpeg", DeviceType.NOTEBOOK),
            Model(4L, "MateBook D 15 AMD BOHL-WDQ9", 3L,
                "https://content2.onliner.by/catalog/device/header/876398d10c2d64f6cfbca1c700db96d6.jpeg", DeviceType.NOTEBOOK),
            Model(5L, "RedmiBook 16 JYU4275CN", 4L,
                "https://content2.onliner.by/catalog/device/header/61587c9c80975112b30023e3a3c90a36.jpeg", DeviceType.NOTEBOOK),
            Model(6L, "TUF Gaming FX505DT-HN450", 5L,
                "https://content2.onliner.by/catalog/device/header/5d591589287bdfe724068e346033c6cc.jpeg", DeviceType.NOTEBOOK),
            Model(7L, "ProBook 455 G7 1L3H0EA", 6L,
                "https://content2.onliner.by/catalog/device/header/7635564c01b7188904ff90194d09b67f.jpeg", DeviceType.NOTEBOOK),
        )
        val specs = listOf(
            Spec(1L, 1L, 8, 256, "M1", "grey", "13.3 2560x1600", true),
            Spec(2L, 1L, 16, 512, "M1", "gold", "13.3 2560x1600", false),
            Spec(3L, 2L, 16, 512, "Intel Core I7", "grey", "16.0 3072x1920", true),
            Spec(4L, 2L, 32, 1024, "Intel Core I9", "silver", "16.0 3072x1920", false),
            Spec(5L, 3L, 8, 512, "Intel Core I5", "silver", "14.0 2560x1600", true),
            Spec(6L, 4L, 8, 512, "AMD Ryzen 5", "silver", "15.6 1920x1080", true),
            Spec(7L, 5L, 16, 512, "AMD Ryzen 7", "dark gray", "16 1920x1080", true),
            Spec(8L, 6L, 8, 512, "AMD Ryzen 5", "black", "15.6 1920x1080", true),
            Spec(9L, 7L, 8, 512, "AMD Ryzen 7", "silver", "15.6 1920x1080", true),
        )

        withContext(Dispatchers.IO) {
            countries.forEach { country ->
                dao.insertCountry(country)
            }
            brands.forEach { brand ->
                dao.insertBrand(brand)
            }
            models.forEach { model ->
                dao.insertModel(model)
            }
            specs.forEach { spec ->
                dao.insertSpec(spec)
            }
        }
    }
}