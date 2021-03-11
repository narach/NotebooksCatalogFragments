package by.itacademy.notebookscatalog.fragments.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDeviceEditBinding
import by.itacademy.notebookscatalog.fragments.listeners.OnFragmentCommunicationListener
import by.itacademy.notebookscatalog.fragments.transformers.DeviceToDeviceItemTransformer
import by.itacademy.notebookscatalog.fragments.viewmodels.DeviceListViewModel

class DeviceEditFragment(private val fragmentNavigation: OnFragmentCommunicationListener) : Fragment(R.layout.fragment_device_edit) {

    private val selImgCode = 1

    private var imgUri: Uri? = null

    private val logTag = "DeviceAddFragment"

    private var _binding: FragmentDeviceEditBinding? = null
    private val binding get() = _binding!!

    private val deviceListViewModel: DeviceListViewModel by activityViewModels()
    private var selectedDevice: DeviceItem = DeviceItem(null, "Model", null, null)

    private var selectedItemId: Int? = null

    fun selectItem(selectedIndex: Int) {
        selectedItemId = selectedIndex
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView is called")
        _binding = FragmentDeviceEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            deviceListViewModel.devicesListLiveData.observe(
                viewLifecycleOwner,
                Observer<MutableList<DeviceItem>> { deviceList ->
                    etModelEdit.setText(deviceList[deviceListViewModel.selectedIndex.value!!].model)
                    etScreenEdit.setText(deviceList[deviceListViewModel.selectedIndex.value!!].screen)
                    etHardwareEdit.setText(deviceList[deviceListViewModel.selectedIndex.value!!].hardware)
                    ivDevice.setImageDrawable(deviceList[deviceListViewModel.selectedIndex.value!!].img)
                }
            )

            btnSave.setOnClickListener {
                selectedDevice.hardware = etHardwareEdit.text.toString()
                selectedDevice.screen = etScreenEdit.text.toString()
                selectedDevice.model = etModelEdit.text.toString()
                selectedDevice.img = DeviceToDeviceItemTransformer.uriToDrawable(imgUri.toString(), requireContext())
                deviceListViewModel.updateDeviceAtPosition(selectedDevice, deviceListViewModel.selectedIndex.value!!)
                fragmentNavigation.listDevices()
            }

            ivDevice.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, selImgCode)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == selImgCode) {
            imgUri = data?.data
            binding.ivDevice.setImageURI(imgUri)
            selectedDevice.img = DeviceToDeviceItemTransformer.uriToDrawable(imgUri.toString(), requireContext())
        }
    }
}