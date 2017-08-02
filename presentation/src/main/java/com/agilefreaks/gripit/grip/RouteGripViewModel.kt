package com.agilefreaks.gripit.grip

import android.databinding.BaseObservable
import android.view.View
import com.agilefreaks.gripit.core.Lifecycle

class RouteGripViewModel : BaseObservable(), RouteGripContract.ViewModel{
    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }

    @Suppress("UNUSED_PARAMETER")
    fun onIconClick(view: View) {

    }
}