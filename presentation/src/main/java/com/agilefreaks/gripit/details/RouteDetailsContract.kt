package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.details.picture.RoutePictureFragment

class RouteDetailsContract {
    interface View : Lifecycle.View {
        fun getRouteId(): Int
        fun setPictureNavigation(fragment: RoutePictureFragment)
    }

    interface ViewModel : Lifecycle.ViewModel {
    }
}