package by.itacademy.notebookscatalog.fragments.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.adapters.DevicesListAdapter
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDevicesListBinding
import by.itacademy.notebookscatalog.fragments.transformers.DeviceToDeviceItemTransformer

class DevicesListFragment : Fragment(R.layout.fragment_devices_list) {

    private val logTag = "DevicesListFragment"

    private var _binding: FragmentDevicesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var fContext: Context
    private lateinit var devicesListAdapter: DevicesListAdapter

    private var devicesList = mutableListOf<DeviceItem>()

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach is called")
        super.onAttach(context)
        this.fContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView is called")
        _binding = FragmentDevicesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(devicesList.isEmpty()) {
            initDevicesList()

            devicesListAdapter = DevicesListAdapter(devicesList)
        }
        binding.rvNotesList.adapter = devicesListAdapter
        binding.rvNotesList.layoutManager = LinearLayoutManager(fContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onDeviceCreated(device: Device) {
        val deviceItem = DeviceToDeviceItemTransformer.transform(device, fContext)
        devicesList.add(deviceItem)
        devicesListAdapter.notifyItemInserted(devicesList.size - 1)
    }

    private fun initDevicesList() {
        devicesList = mutableListOf(
            DeviceItem(
                ContextCompat.getDrawable(fContext, R.drawable.asus_tuf),
                "Asus TUF Gaming F15",
                "15.6, 1920 x 1080",
                "Intel Core I5, 16Gb RAM, 512Gb SSD, 4Gb Video"
            ),
            DeviceItem(
                ContextCompat.getDrawable(fContext, R.drawable.honor_magic_book),
                "Honor Magic 14 2020 53010 VTY",
                "14.0, 1920 x 1080",
                "AMD Ryzen 5, 8Gb RAM, 512Gb SSD"
            ),
            DeviceItem(
                ContextCompat.getDrawable(fContext, R.drawable.apple_mac_book),
                "Apple Macbook Air 13 M1 2020",
                "13.3, 2560 x 1600",
                "Apple M1, 8Gb RAM, 256Gb SSD, M1 GPU Video"
            )
        )
    }
}