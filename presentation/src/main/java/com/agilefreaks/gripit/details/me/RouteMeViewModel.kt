package com.agilefreaks.gripit.details.me

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import javax.inject.Inject


class RouteMeViewModel @Inject constructor() : BaseObservable(), RouteMeContract.ViewModel {

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }
}