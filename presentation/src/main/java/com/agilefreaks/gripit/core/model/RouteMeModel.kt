package com.agilefreaks.gripit.core.model

import android.databinding.BaseObservable

class RouteMeModel constructor(imageLocation: String = "", date: String = "") : BaseObservable() {
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

}