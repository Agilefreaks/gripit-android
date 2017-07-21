package com.agilefreaks.gripit.details.info

import com.agilefreaks.gripit.core.Lifecycle

class RouteInfoContract {
    interface View : Lifecycle.View {
        fun getRouteId(): Int
    }

    interface ViewModel : Lifecycle.ViewModel
}