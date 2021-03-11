package by.itacademy.notebookscatalog.fragments.viewmodels

import androidx.lifecycle.ViewModel
import by.itacademy.notebookscatalog.fragments.data.DeviceItem

class DeviceListViewModel : ViewModel() {
    var devicesList = mutableListOf<DeviceItem>()

    var selectedIndex: Int? = null
}