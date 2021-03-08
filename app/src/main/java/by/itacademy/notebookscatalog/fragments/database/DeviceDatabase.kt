package by.itacademy.notebookscatalog.fragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.itacademy.notebookscatalog.fragments.database.dao.DeviceDao
import by.itacademy.notebookscatalog.fragments.database.entities.*

@Database(
    entities = [
        Country::class,
        Brand::class,
        Model::class,
        Spec::class
    ],
    version = 1
)
@TypeConverters(DeviceTypeConverter::class)
abstract class DeviceDatabase : RoomDatabase() {

    abstract val deviceDao: DeviceDao

    companion object {
        @Volatile
        private var INSTANCE: DeviceDatabase? = null

        fun getInstance(context: Context): DeviceDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DeviceDatabase::class.java,
                    "device_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}