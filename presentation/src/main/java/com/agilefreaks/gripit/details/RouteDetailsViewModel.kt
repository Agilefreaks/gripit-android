package com.agilefreaks.gripit.details

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import javax.inject.Inject


class RouteDetailsViewModel @Inject constructor() : BaseObservable(), RouteDetailsContract.ViewModel {
    lateinit var viewCallback: RouteDetailsContract.View
    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteDetailsContract.View
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }

    override fun setRouteId(routeId: Int) {
    }
}