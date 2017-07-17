package com.agilefreaks.gripit.routes.list

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.Types
import com.agilefreaks.gripit.domain.interactor.GetRoutes
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class ListViewModel @Inject constructor(val useCase: GetRoutes) : BaseObservable(), ListContract.ViewModel {
    lateinit var viewCallback: ListContract.View

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as ListContract.View
        filter(Types.NoFilter.toString())
    }

    override fun onViewDetached() {
    }

    override fun onViewResume() {
    }

    override fun onViewPaused() {
    }

    override fun filter(filter: String) {
        useCase.execute(RoutesObserver(), filter)
    }

    inner class RoutesObserver : DisposableObserver<Collection<Route>>() {
        override fun onComplete() {
            // ignore
        }

        override fun onError(e: Throwable?) {
        }

        override fun onNext(routes: Collection<Route>) {
            viewCallback.showRoutes(routes)
        }
    }
}
