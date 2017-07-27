package com.agilefreaks.gripit.routes.list

import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.Route

interface ListContract {
    interface View : Lifecycle.View {
        fun showRoutes(routes: Collection<Route>)
    }

    interface ViewModel : Lifecycle.ViewModel {
        fun filter(filter: String)

        fun dispose()
    }
}