package by.itacademy.notebookscatalog.fragments.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.communication.OnDeviceCreatedListener
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceType
import by.itacademy.notebookscatalog.fragments.data.Screen
import by.itacademy.notebookscatalog.fragments.databinding.FragmentDeviceAddBinding
import java.lang.RuntimeException

class DeviceAddFragment : Fragment(R.layout.fragment_device_add), AdapterView.OnItemSelectedListener {

    val SELECT_IMAGE_CODE = 2

    var imgUri: Uri? = null

    private val logTag = "DeviceAddFragment"

    private var _binding: FragmentDeviceAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mListener: OnDeviceCreatedListener
    private lateinit var fContext: Context
    private var newDevice: Device? = null

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach is called")
        super.onAttach(context)
        fContext = context
        if(context is OnDeviceCreatedListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentCommunicationListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView is called")
        _binding = FragmentDeviceAddBinding.inflate(inflater, container, false)
        newDevice = Device(null, null, null, 0, 0, null, null, null)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val procAdapter = ArrayAdapter.createFromResource(
            fContext,
            R.array.proc_models,
            android.R.layout.simple_spinner_dropdown_item
        )
        procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spProcModel.adapter = procAdapter
        binding.spProcModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newDevice?.processor = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                newDevice?.processor = null
            }
        }

        ArrayAdapter.createFromResource(
            fContext,
            R.array.device_models,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spBrand.adapter = adapter
        }

        binding.spBrand.onItemSelectedListener = this

        with(binding) {
            etModel.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(modelValue: CharSequence?, start: Int, before: Int, count: Int) {
                    newDevice?.model = modelValue.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

        binding.rgDeviceTypes.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.rbNotebook -> newDevice?.deviceType = DeviceType.NOTEBOOK
                R.id.rbSmartphone -> newDevice?.deviceType = DeviceType.SMARTPHONE
                R.id.rbTablet -> newDevice?.deviceType = DeviceType.TABLET
            }
        }
        newDevice?.deviceType = DeviceType.NOTEBOOK

        binding.sbRam.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvRam.text = "$progress Gb"
                newDevice?.ram = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.slDrive.addOnChangeListener { slider, value, fromUser ->
            Log.d(logTag, "Drive size: ${value}")
            binding.etDriveSize.setText(value.toInt().toString())
            newDevice?.drive = value.toInt()
        }

        binding.etDriveSize.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val driveSizeValue = s.toString().toFloat()
                binding.slDrive.value = driveSizeValue
            }
            override fun afterTextChanged(s: Editable?) {}
        }}

        binding.ivNoteImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_CODE)
        }

        binding.btnCreate.setOnClickListener {
            newDevice?.let { device ->
                Log.d(logTag, device.toString())
                mListener.onDeviceCreated(newDevice)
            }
        }

        binding.spSize.prompt = "Screen Size(inches)"
        val screenSizeAdapter = ArrayAdapter.createFromResource(
            fContext,
            R.array.screens,
            android.R.layout.simple_spinner_dropdown_item
        )
        procAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSize.adapter = screenSizeAdapter
        binding.spSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newDevice?.screen?.size = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                newDevice?.screen = Screen()
            }
        }

        binding.spResolution.prompt = "Screen resolution"
        binding.spResolution.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newDevice?.screen?.resolution = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                newDevice?.screen = Screen()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == SELECT_IMAGE_CODE) {
            imgUri = data?.data
            binding.ivNoteImg.setImageURI(imgUri)
            newDevice?.imageUri = imgUri.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        newDevice?.brand = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        newDevice?.brand = null
    }
}