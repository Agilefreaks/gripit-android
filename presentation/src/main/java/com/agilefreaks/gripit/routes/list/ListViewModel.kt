package com.agilefreaks.gripit.routes.list

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.Types
import com.agilefreaks.gripit.domain.interactor.DefaultObserver
import com.agilefreaks.gripit.domain.interactor.GetRoutes
import javax.inject.Inject

class ListViewModel @Inject constructor(val useCase: GetRoutes) : BaseObservable(), ListContract.ViewModel {
    lateinit var viewCallback: ListContract.View
    var cachedFilter: String = Types.NoFilter.toString()

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as ListContract.View
        filter(cachedFilter)
    }

    override fun onViewDetached() {
        useCase.dispose()
    }

    override fun onViewResume() {
    }

    override fun onViewPaused() {
    }

    override fun filter(filter: String) {
        cachedFilter = filter
        useCase.execute(RoutesObserver(), filter)
    }

    inner class RoutesObserver : DefaultObserver<Collection<Route>>() {
        override fun onNext(item: Collection<Route>) {
            viewCallback.showRoutes(item)
        }
    }
}
