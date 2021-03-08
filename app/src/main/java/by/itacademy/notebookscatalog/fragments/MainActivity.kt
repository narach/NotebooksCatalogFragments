package by.itacademy.notebookscatalog.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.itacademy.notebookscatalog.fragments.communication.OnDeviceCreatedListener
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.database.InitHelper
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding
import by.itacademy.notebookscatalog.fragments.ui.DeviceAddFragment
import by.itacademy.notebookscatalog.fragments.ui.DeviceEditFragment
import by.itacademy.notebookscatalog.fragments.ui.DevicesListFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnDeviceCreatedListener  {

    private lateinit var binding: ActivityMainBinding
    lateinit var fDevicesList: DevicesListFragment
    private lateinit var fDeviceAdd: DeviceAddFragment
    lateinit var fDeviceEdit: DeviceEditFragment

    lateinit var deviceViewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        // Load data from device_db or create a new instance of device_db if it doesn't exist
        lifecycleScope.launch {
            InitHelper.createDb(applicationContext)
        }

        deviceViewModel = ViewModelProvider(this).get(DeviceViewModel::class.java)

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