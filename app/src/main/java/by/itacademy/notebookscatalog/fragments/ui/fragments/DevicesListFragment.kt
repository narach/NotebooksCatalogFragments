package by.itacademy.notebookscatalog.fragments.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceViewModel
import by.itacademy.notebookscatalog.fragments.ui.activities.MainActivity
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.adapters.DevicesListAdapter
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDevicesListBinding
import by.itacademy.notebookscatalog.fragments.transformers.DeviceToDeviceItemTransformer
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListViewModel

class DevicesListFragment : Fragment(R.layout.fragment_devices_list) {

    private val logTag = "DevicesListFragment"

    private var _binding: FragmentDevicesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var fContext: Context
    private var devicesListAdapter: DevicesListAdapter? = null

    private lateinit var deviceViewModel: DeviceViewModel
    private val deviceListViewModel: DeviceListViewModel by activityViewModels()

    lateinit var adaptorItemSelectListener: (DeviceItem) -> Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            deviceViewModel = ViewModelProvider(it!!).get(DeviceViewModel::class.java)
        }
    }

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

        if(devicesListAdapter == null) {
            var devicesList = deviceListViewModel.devicesList
            devicesListAdapter = DevicesListAdapter(devicesList, adaptorItemSelectListener, fContext as MainActivity)
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
        deviceListViewModel.devicesList.add(deviceItem)
        devicesListAdapter?.notifyItemInserted(deviceListViewModel.devicesList.size - 1)
    }

}