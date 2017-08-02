package com.agilefreaks.gripit.grip

import android.net.Uri
import com.agilefreaks.gripit.core.Lifecycle

class RouteGripContract {
    interface View : Lifecycle.View {
        fun loadFromGallery()
        fun getRealPath(contentURI: Uri): String
    }

    interface ViewModel : Lifecycle.ViewModel {
        fun handleGalleryResult(filePath: String)
    }
}