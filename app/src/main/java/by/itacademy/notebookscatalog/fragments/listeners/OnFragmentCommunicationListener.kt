package by.itacademy.notebookscatalog.fragments.listeners

import by.itacademy.notebookscatalog.fragments.data.Device

interface OnFragmentCommunicationListener {
    fun createDevice()
    fun updateDevice(index: Int)
    fun listDevices()
    fun onDeviceCreated(device: Device?)
}