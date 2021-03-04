package by.itacademy.notebookscatalog.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding
import by.itacademy.notebookscatalog.fragments.ui.DeviceAddFragment
import by.itacademy.notebookscatalog.fragments.ui.DevicesListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fDevicesList: DevicesListFragment
    private lateinit var fDeviceAdd: DeviceAddFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        fDevicesList = DevicesListFragment()
        fDeviceAdd = DeviceAddFragment()
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

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}