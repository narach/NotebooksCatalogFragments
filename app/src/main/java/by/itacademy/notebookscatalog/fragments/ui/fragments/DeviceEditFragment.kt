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
    private lateinit var selectedDevice: DeviceItem

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
        selectedDevice = deviceListViewModel.devicesList[deviceListViewModel.selectedIndex!!]

        with(binding) {
            etModelEdit.setText(selectedDevice.model)
            etScreenEdit.setText(selectedDevice.screen)
            etHardwareEdit.setText(selectedDevice.hardware)
            ivDevice.setImageDrawable(selectedDevice.img)

            btnSave.setOnClickListener {
                selectedDevice.hardware = etHardwareEdit.text.toString()
                selectedDevice.screen = etScreenEdit.text.toString()
                selectedDevice.model = etModelEdit.text.toString()
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