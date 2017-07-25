package com.agilefreaks.gripit.details.picture

import com.agilefreaks.gripit.core.Lifecycle

class RoutePictureContract {
    interface View : Lifecycle.View {
        fun getRoutePictureLocation(): String
    }

    interface ViewModel : Lifecycle.ViewModel
}