package com.agilefreaks.gripit.routes.tabs

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.support.design.widget.TabLayout
import com.agilefreaks.gripit.domain.None
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.domain.interactor.DefaultObserver
import com.agilefreaks.gripit.domain.interactor.GetFilter
import com.agilefreaks.gripit.routes.RoutesController
import io.reactivex.observers.DisposableObserver
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject


class TabsViewModel @Inject constructor(val useCase: GetFilter) : BaseObservable(), TabsContract.ViewModel {
    override val tabSelected: BehaviorProcessor<String> = BehaviorProcessor.create<String>()
    override val routeFilters = ObservableField<Collection<RouteFilter>>(emptyList())

    companion object {
        @JvmStatic @BindingAdapter("android:tabs")
        fun setTabs(tabs: TabLayout, routeFilters: Collection<RouteFilter>) {
            routeFilters.forEach {
                val tab = tabs.newTab()
                tab.text = it.type
                tab.tag = it.type
                tabs.addTab(tab)
            }
        }
    }

    override fun onViewAttached(viewCallback: com.agilefreaks.gripit.core.Lifecycle.View) {
        useCase.executeWithConsumer(Consumer({ routeFilters.set(it) }), None())
    }

    override fun onViewDetached() {
        useCase.dispose()
    }

    override fun onViewPaused() {
    }

    override fun onViewResume() {
    }

    override fun listenToTabSelection(selectionObservable: Observable<TabLayout.Tab>) {
        selectionObservable.subscribe({ tabSelected.onNext(it.tag.toString()) })
    }
}