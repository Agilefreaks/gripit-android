package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.Lifecycle

class RouteDetailsContract {
    interface View : Lifecycle.View {
        fun getRouteId(): Int
        fun setTabLayout()
    }

    interface ViewModel : Lifecycle.ViewModel
}