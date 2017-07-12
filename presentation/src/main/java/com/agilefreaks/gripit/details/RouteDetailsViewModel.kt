package com.agilefreaks.gripit.details

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import javax.inject.Inject


class RouteDetailsViewModel @Inject constructor() : BaseObservable(), RouteDetailsContract.ViewModel {
    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }
}