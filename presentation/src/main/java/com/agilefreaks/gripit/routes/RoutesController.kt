package com.agilefreaks.gripit.routes

import android.content.Context
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import javax.inject.Inject

class RoutesController @Inject constructor(val listViewModel: ListContract.ViewModel, val tabsViewModel: TabsContract.ViewModel, val navigator: Navigator) {
    @Inject lateinit var context: Context

    fun setup() {
    }

    fun onResume() {
        tabsViewModel.tabSelected.subscribe({ listViewModel.filter(it) })
    }

    fun navigate(route: Route) {
        navigator.navigateToRouteDetails(route.id)
    }

    fun onDestroy() {
        tabsViewModel.dispose()
        listViewModel.dispose()
    }
}