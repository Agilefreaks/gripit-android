package com.agilefreaks.gripit.details.me

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.interactor.GetRouteMe
import io.reactivex.functions.Consumer
import javax.inject.Inject


class RouteMeViewModel @Inject constructor(val useCase: GetRouteMe) : BaseObservable(), RouteMeContract.ViewModel {
    lateinit var viewCallback: RouteMeContract.View

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteMeContract.View
        useCase.executeWithConsumer(Consumer { viewCallback.addRouteMe(it) }, this.viewCallback.getRouteId())
    }

    override fun onViewDetached() {
        useCase.dispose()
    }

    override fun onViewPaused() {
    }
}