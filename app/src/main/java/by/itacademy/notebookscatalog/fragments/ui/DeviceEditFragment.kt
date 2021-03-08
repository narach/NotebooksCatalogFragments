package by.itacademy.notebookscatalog.fragments.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.itacademy.notebookscatalog.fragments.DeviceViewModel
import by.itacademy.notebookscatalog.fragments.MainActivity
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDeviceEditBinding
import by.itacademy.notebookscatalog.fragments.transformers.DeviceToDeviceItemTransformer
import com.google.android.material.bottomnavigation.BottomNavigationView

class DeviceEditFragment : Fragment(R.layout.fragment_device_edit) {

    private val selImgCode = 1

    private var imgUri: Uri? = null

    private val logTag = "DeviceAddFragment"

    private var _binding: FragmentDeviceEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var deviceViewModel: DeviceViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            deviceViewModel = ViewModelProvider(it!!).get(DeviceViewModel::class.java)
            with(binding) {
                etModelEdit.setText(deviceViewModel.selected.value?.model)
                etScreenEdit.setText(deviceViewModel.selected.value?.screen)
                etHardwareEdit.setText(deviceViewModel.selected.value?.hardware)
                ivDevice.setImageDrawable(deviceViewModel.selected.value?.img)
            }
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
        with(binding) {
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

            btnBack.setOnClickListener {
                val mainActivity = activity as MainActivity
                val bottomMenu = mainActivity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomMenu.selectedItemId = R.id.miHome
                mainActivity.setCurrentFragment(mainActivity.fDevicesList)
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
            deviceViewModel.selected.value?.img = DeviceToDeviceItemTransformer.uriToDrawable(imgUri.toString(), context!!)
        }
    }
}