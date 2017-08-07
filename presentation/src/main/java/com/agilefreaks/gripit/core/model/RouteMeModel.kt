package com.agilefreaks.gripit.core.model

import android.databinding.BaseObservable
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class RouteMeModel(imageLocation: String = "", date: String = "", val gripped: Boolean) : BaseObservable() {
    var imageLocation: String = imageLocation
        set(value) {
            field = value
            notifyChange()
        }

    var date = date
        set(value) {
            field = value
            notifyChange()
        }
        get() {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val time = dateFormat.parse(field)
            if (DateUtils.isToday(time.time)) {
                return "Today at " + SimpleDateFormat("HH:mm", Locale.getDefault()).format(time)
            } else {
                return field
            }
        }

}