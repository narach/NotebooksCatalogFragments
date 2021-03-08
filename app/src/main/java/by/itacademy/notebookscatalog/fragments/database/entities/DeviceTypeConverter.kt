package by.itacademy.notebookscatalog.fragments.database.entities

import androidx.room.TypeConverter
import by.itacademy.notebookscatalog.fragments.data.DeviceType

class DeviceTypeConverter {
    @TypeConverter
    fun toDeviceType(value: Int) = enumValues<DeviceType>()[value]

    @TypeConverter
    fun fromDeviceType(value: DeviceType) = value.ordinal
}