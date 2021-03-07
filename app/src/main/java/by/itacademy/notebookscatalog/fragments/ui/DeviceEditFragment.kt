package by.itacademy.notebookscatalog.fragments.ui

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.itacademy.notebookscatalog.fragments.DeviceViewModel
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDeviceEditBinding

class DeviceEditFragment : Fragment(R.layout.fragment_device_edit) {

    val selImgCode = 1

    var imgUri: Uri? = null

    private val logTag = "DeviceAddFragment"

    private var _binding: FragmentDeviceEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var deviceViewModel: DeviceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO - Check, if it should be initialized here
        activity.let {
            deviceViewModel = ViewModelProvider(it!!).get(DeviceViewModel::class.java)
        }
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
        deviceViewModel = ViewModelProvider(activity!!).get(DeviceViewModel::class.java)
        with(binding) {
            etModelEdit.setText(deviceViewModel.selected.value?.model)
            etScreenEdit.setText(deviceViewModel.selected.value?.screen)
            etHardwareEdit.setText(deviceViewModel.selected.value?.hardware)

            etModelEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(newValue: CharSequence?, start: Int, before: Int, count: Int) {
                    deviceViewModel.selected.value?.model = newValue.toString()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })

            etHardwareEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(newValue: CharSequence?, start: Int, before: Int, count: Int) {
                    deviceViewModel.selected.value?.hardware = newValue.toString()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })

            etScreenEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(newValue: CharSequence?, start: Int, before: Int, count: Int) {
                    deviceViewModel.selected.value?.screen = newValue.toString()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }
}