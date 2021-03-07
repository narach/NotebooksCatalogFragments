package by.itacademy.notebookscatalog.fragments.transformers

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.ContextCompat
import by.itacademy.notebookscatalog.fragments.R
import by.itacademy.notebookscatalog.fragments.data.Device
import by.itacademy.notebookscatalog.fragments.data.DeviceItem

object DeviceToDeviceItemTransformer {
    fun transform(device: Device, context: Context) : DeviceItem {
        return DeviceItem(
            uriToDrawable(device.imageUri, context),
            "${device.brand} ${device.model}",
            "",
            "${device.processor}, RAM: ${device.ram}Gb, ${device.drive} Gb SSD"
        )
    }

    private fun uriToDrawable(uri: String?, context: Context): Drawable? {
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(Uri.parse(uri))
            return Drawable.createFromStream(inputStream, uri)
        }
        return ContextCompat.getDrawable(context,R.drawable.empty_device);
    }
}