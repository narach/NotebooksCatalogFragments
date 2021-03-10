package by.itacademy.notebookscatalog.fragments.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceViewModel
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.communication.OnDeviceCreatedListener
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding
import by.itacademy.notebookscatalog.fragments.helpers.InitHelper
import by.itacademy.notebookscatalog.fragments.ui.fragments.DeviceAddFragment
import by.itacademy.notebookscatalog.fragments.ui.fragments.DeviceEditFragment
import by.itacademy.notebookscatalog.fragments.ui.fragments.DevicesListFragment
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListViewModel

class MainActivity : AppCompatActivity(), OnDeviceCreatedListener  {

    private lateinit var binding: ActivityMainBinding
    lateinit var fDevicesList: DevicesListFragment
    private lateinit var fDeviceAdd: DeviceAddFragment
    lateinit var fDeviceEdit: DeviceEditFragment

    lateinit var deviceViewModel: DeviceViewModel
    private val deviceListViewModel: DeviceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        deviceViewModel = ViewModelProvider(this).get(DeviceViewModel::class.java)
        deviceListViewModel.devicesList = InitHelper.initDevicesList(this)

        fDevicesList = DevicesListFragment()
        fDevicesList.adaptorItemSelectListener = this::onDeviceItemSelected
        fDeviceAdd = DeviceAddFragment()
        fDeviceEdit = DeviceEditFragment()
        setCurrentFragment(fDevicesList)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.miHome -> setCurrentFragment(fDevicesList)
                R.id.miAdd -> setCurrentFragment(fDeviceAdd)
                R.id.miProfile -> Toast.makeText(this, "Switch to Profile Fragment", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onDeviceCreated(device: Device?) {
        device?.let {
            fDevicesList.onDeviceCreated(it)
        }
    }

    fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    private fun onDeviceItemSelected(deviceItem: DeviceItem) {
        deviceViewModel.select(deviceItem)
        setCurrentFragment(fDeviceEdit)
    }

}