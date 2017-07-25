package com.agilefreaks.gripit.details.picture

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle

class RoutePictureViewModel : BaseObservable(), RoutePictureContract.ViewModel {
    lateinit var viewCallback: RoutePictureContract.View
    var routeImageLocation = ""

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RoutePictureContract.View
        routeImageLocation = viewCallback.getRoutePictureLocation()
        notifyChange()
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }
}