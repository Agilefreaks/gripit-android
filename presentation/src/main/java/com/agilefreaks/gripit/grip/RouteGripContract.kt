package com.agilefreaks.gripit.grip

import android.net.Uri
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.core.model.RouteState

class RouteGripContract {
    interface View : Lifecycle.View {
        fun loadFromGallery()
        fun getRouteState(): RouteState
        fun getRouteId(): Int
    }

    interface ViewModel : Lifecycle.ViewModel {
        fun handleGalleryResult(data: Uri)
    }
}