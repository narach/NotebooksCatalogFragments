package by.itacademy.notebookscatalog.fragments.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.helpers.InitHelper

class DeviceListVM(app: Application) : AndroidViewModel(app) {
    private lateinit var devices: MutableLiveData<List<DeviceItem>>

    fun getDevices(): MutableLiveData<List<DeviceItem>> {
        if (!::devices.isInitialized) {
            devices = MutableLiveData()
            loadDevices()
        }
        return devices
    }

    private fun loadDevices() {
        devices.value = InitHelper.initDevicesList(this.getApplication())
    }
}