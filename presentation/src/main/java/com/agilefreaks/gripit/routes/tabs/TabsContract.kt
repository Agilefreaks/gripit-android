package com.agilefreaks.gripit.routes.tabs

import android.databinding.ObservableField
import android.support.design.widget.TabLayout
import android.view.View
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.routes.RoutesController
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

interface TabsContract {
    interface View : Lifecycle.View

    interface ViewModel : Lifecycle.ViewModel {
        val routeFilters: ObservableField<Collection<RouteFilter>>

        val tabSelected: BehaviorProcessor<String>

        fun listenToTabSelection(selectionObservable: Observable<TabLayout.Tab>)
    }
}