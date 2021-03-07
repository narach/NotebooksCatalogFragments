package by.itacademy.notebookscatalog.fragments.communication

import by.itacademy.notebookscatalog.fragments.data.Device

interface OnDeviceCreatedListener {
    fun onDeviceCreated(device: Device?)
}