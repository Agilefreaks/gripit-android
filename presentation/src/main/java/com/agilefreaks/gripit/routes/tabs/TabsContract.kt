package com.agilefreaks.gripit.routes.tabs

import android.databinding.ObservableField
import android.support.design.widget.TabLayout
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.RouteFilter
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor

interface TabsContract {
    interface View : Lifecycle.View

    interface ViewModel : Lifecycle.ViewModel {
        val routeFilters: ObservableField<Collection<RouteFilter>>

        val tabSelected: BehaviorProcessor<String>

        fun listenToTabSelection(selectionObservable: Observable<TabLayout.Tab>)
    }
}