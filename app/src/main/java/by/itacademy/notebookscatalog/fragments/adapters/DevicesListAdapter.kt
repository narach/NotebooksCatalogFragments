package by.itacademy.notebookscatalog.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.notebookscatalog.fragments.MainActivity
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.data.DeviceItem
import by.itacademy.notebookscatalog.fragments.databinding.ItemDeviceBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DevicesListAdapter(
    private var devicesList: List<DeviceItem>,
    private val listener: (DeviceItem) -> Unit,
    private val context: MainActivity
) : RecyclerView.Adapter<DevicesListAdapter.DevicesViewHolder>() {

    private val logTag = "DevicesListAdapter"

    private var selectedItem: ItemDeviceBinding? = null

    inner class DevicesViewHolder(val itemDeviceBinding: ItemDeviceBinding) : RecyclerView.ViewHolder(itemDeviceBinding.root) {

        fun bind(deviceItem: DeviceItem) {
            itemDeviceBinding.ivDevice.setImageDrawable(deviceItem.img)
            itemDeviceBinding.tvModel.text = deviceItem.model
            itemDeviceBinding.tvScreen.text = deviceItem.screen
            itemDeviceBinding.tvHardware.text = deviceItem.hardware
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemDeviceBinding = ItemDeviceBinding.inflate(layoutInflater, parent, false)
        return DevicesViewHolder(itemDeviceBinding)
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(devicesList[position])
        holder.itemView.setOnClickListener {
            holder.itemDeviceBinding.cbSelected.isChecked = true
            selectedItem?.let { item ->
                item.cbSelected.isChecked = false
            }
            selectedItem = holder.itemDeviceBinding
            var selectedItem = devicesList[position]
            context.deviceViewModel.select(selectedItem)
            val bottomMenu = context.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomMenu.selectedItemId = R.id.miEdit
            context.setCurrentFragment(context.fDeviceEdit)
        }
//        holder.itemView.setOnClickListener {
//            listener(devicesList[position])
//        }
    }

    override fun getItemCount(): Int {
        return devicesList.size
    }
}