package com.agilefreaks.gripit.details.me

import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.RouteMe

class RouteMeContract {
    interface View : Lifecycle.View {
        fun addRouteMe(routeMeList: Collection<RouteMe>)
        fun getRouteId(): Int
    }

    interface ViewModel : Lifecycle.ViewModel
}