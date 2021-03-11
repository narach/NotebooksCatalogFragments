package by.itacademy.notebookscatalog.fragments.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.adapters.DevicesListAdapter
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDevicesListBinding
import by.itacademy.notebookscatalog.fragments.listeners.OnFragmentCommunicationListener
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListViewModel

class DevicesListFragment(val fragmentNavigation: OnFragmentCommunicationListener) : Fragment(R.layout.fragment_devices_list) {

    private val logTag = "DevicesListFragment"

    private var _binding: FragmentDevicesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var fContext: Context
    private var devicesListAdapter: DevicesListAdapter? = null

    private val deviceListViewModel: DeviceListViewModel by activityViewModels()

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
        devicesListAdapter = DevicesListAdapter(deviceListViewModel.devicesList, fragmentNavigation)
        binding.rvNotesList.adapter = devicesListAdapter
        binding.rvNotesList.layoutManager = LinearLayoutManager(fContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}