package by.itacademy.notebookscatalog.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.notebookscatalog.fragments.data.NotebookItem
import by.itacademy.notebookscatalog.fragments.databinding.ItemDeviceBinding

class DevicesListAdapter(
    private var devicesList: List<NotebookItem>
) : RecyclerView.Adapter<DevicesListAdapter.DevicesViewHolder>() {

    private val logTag = "DevicesListAdapter"

    inner class DevicesViewHolder(private val itemDeviceBinding: ItemDeviceBinding) : RecyclerView.ViewHolder(itemDeviceBinding.root) {

        fun bind(notebookItem: NotebookItem) {
            itemDeviceBinding.ivDevice.setImageDrawable(notebookItem.img)
            itemDeviceBinding.tvModel.text = notebookItem.model
            itemDeviceBinding.tvScreen.text = notebookItem.screen
            itemDeviceBinding.tvHardware.text = notebookItem.hardware
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemDeviceBinding = ItemDeviceBinding.inflate(layoutInflater, parent, false)
        return DevicesViewHolder(itemDeviceBinding)
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.bind(devicesList[position])
    }

    override fun getItemCount(): Int {
        return devicesList.size
    }
}