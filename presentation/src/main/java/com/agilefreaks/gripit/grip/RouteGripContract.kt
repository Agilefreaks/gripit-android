package com.agilefreaks.gripit.grip

import android.net.Uri
import com.agilefreaks.gripit.core.Lifecycle

class RouteGripContract {
    interface View : Lifecycle.View {
        fun loadFromGallery()
        fun getRouteState(): String
        fun getRouteId(): Int
    }

    interface ViewModel : Lifecycle.ViewModel {
        fun handleGalleryResult(data: Uri)
    }
}