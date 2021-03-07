package by.itacademy.notebookscatalog.fragments.data

data class Device(
    var brand: String?,
    var model: String?,
    var deviceType: DeviceType?,
    var ram: Int?,
    var drive: Int?,
    var processor: String?,
    var imageUri: String?
)
