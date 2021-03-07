package by.itacademy.notebookscatalog.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itacademy.notebookscatalog.fragments.data.DeviceItem

class DeviceViewModel : ViewModel() {

    val selected = MutableLiveData<DeviceItem>()

    fun select(item: DeviceItem) {
        selected.value = item
    }
}