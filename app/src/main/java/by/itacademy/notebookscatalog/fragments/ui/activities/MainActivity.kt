package by.itacademy.notebookscatalog.fragments.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding
import by.itacademy.notebookscatalog.fragments.helpers.InitHelper
import by.itacademy.notebookscatalog.fragments.listeners.OnFragmentCommunicationListener
import by.itacademy.notebookscatalog.fragments.ui.fragments.DeviceAddFragment
import by.itacademy.notebookscatalog.fragments.ui.fragments.DeviceEditFragment
import by.itacademy.notebookscatalog.fragments.ui.fragments.DevicesListFragment
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListVM
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListViewModel

class MainActivity : AppCompatActivity(), OnFragmentCommunicationListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fDevicesList: DevicesListFragment
    private lateinit var fDeviceAdd: DeviceAddFragment
    private lateinit var fDeviceEdit: DeviceEditFragment

    private val deviceListViewModel: DeviceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        deviceListViewModel.loadDevices(InitHelper.initDevicesList(this))

        fDevicesList = DevicesListFragment(this)
        fDeviceAdd = DeviceAddFragment(this)
        fDeviceEdit = DeviceEditFragment(this)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.miHome -> setCurrentFragment(fDevicesList)
                R.id.miAdd -> setCurrentFragment(fDeviceAdd)
                R.id.miProfile -> Toast.makeText(this, "Switch to Profile Fragment", Toast.LENGTH_SHORT).show()
            }
            true
        }

        setCurrentFragment(fDevicesList)
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    override fun onDeviceCreated(device: Device?) {
        setCurrentFragment(fDevicesList)
    }

    override fun createDevice() {
        binding.bottomNavigationView.selectedItemId = R.id.miAdd
        setCurrentFragment(fDeviceAdd)
    }

    override fun updateDevice(index: Int) {
        deviceListViewModel.selectItem(index)
        binding.bottomNavigationView.selectedItemId = R.id.miEdit
        setCurrentFragment(fDeviceEdit)
    }

    override fun listDevices() {
        binding.bottomNavigationView.selectedItemId = R.id.miHome
        setCurrentFragment(fDevicesList)
    }

}