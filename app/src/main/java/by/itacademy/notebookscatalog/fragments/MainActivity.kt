package by.itacademy.notebookscatalog.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.itacademy.notebookscatalog.fragments.communication.OnDeviceCreatedListener
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding
import by.itacademy.notebookscatalog.fragments.ui.DeviceAddFragment
import by.itacademy.notebookscatalog.fragments.ui.DeviceEditFragment
import by.itacademy.notebookscatalog.fragments.ui.DevicesListFragment

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