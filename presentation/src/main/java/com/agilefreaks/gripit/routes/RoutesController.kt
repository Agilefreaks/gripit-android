package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class RoutesController @Inject constructor(val listViewModel: ListContract.ViewModel, val tabsViewModel: TabsContract.ViewModel) {

    fun setup() {
    }

    fun onResume() {
        tabsViewModel.tabSelected.subscribe({ listViewModel.filter(it) })
    }
}