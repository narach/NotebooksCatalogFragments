package by.itacademy.notebookscatalog.fragments.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.adapters.DevicesListAdapter
import by.itacademy.notebookscatalog.fragments.data.NotebookItem
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDevicesListBinding

class DevicesListFragment : Fragment(R.layout.fragment_devices_list) {

    private val logTag = "DevicesListFragment"

    private var _binding: FragmentDevicesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var fContext: Context
    private lateinit var devicesListAdapter: DevicesListAdapter

    private var devicesList = mutableListOf<NotebookItem>()

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
        initDevicesList()

        devicesListAdapter = DevicesListAdapter(devicesList)
        binding.rvNotesList.adapter = devicesListAdapter
        binding.rvNotesList.layoutManager = LinearLayoutManager(fContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uriToDrawable(uri: String): Drawable {
        val inputStream = fContext.contentResolver.openInputStream(Uri.parse(uri))
        return Drawable.createFromStream(inputStream, uri)
    }

    private fun initDevicesList() {
        devicesList = mutableListOf(
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.asus_tuf),
                "Asus TUF Gaming F15",
                "15.6, 1920 x 1080",
                "Intel Core I5, 16Gb RAM, 512Gb SSD, 4Gb Video"
            ),
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.honor_magic_book),
                "Honor Magic 14 2020 53010 VTY",
                "14.0, 1920 x 1080",
                "AMD Ryzen 5, 8Gb RAM, 512Gb SSD"
            ),
            NotebookItem(
                ContextCompat.getDrawable(fContext, R.drawable.apple_mac_book),
                "Apple Macbook Air 13 M1 2020",
                "13.3, 2560 x 1600",
                "Apple M1, 8Gb RAM, 256Gb SSD, M1 GPU Video"
            )
        )
    }
}