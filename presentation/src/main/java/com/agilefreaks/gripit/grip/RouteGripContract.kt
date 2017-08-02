package com.agilefreaks.gripit.grip

import com.agilefreaks.gripit.core.Lifecycle

class RouteGripContract {
    interface View : Lifecycle.View

    interface ViewModel : Lifecycle.ViewModel
}