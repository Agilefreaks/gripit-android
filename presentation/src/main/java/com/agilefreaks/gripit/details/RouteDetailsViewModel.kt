package com.agilefreaks.gripit.details

import android.databinding.BaseObservable
import android.view.View
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.core.model.RouteModel
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.interactor.DefaultObserver
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import javax.inject.Inject


class RouteDetailsViewModel @Inject constructor(val useCase: GetRouteDetails) : BaseObservable(), RouteDetailsContract.ViewModel {
    @Inject lateinit var routePictureFragment: RoutePictureFragment
    lateinit var viewCallback: RouteDetailsContract.View
    lateinit var route: RouteModel

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteDetailsContract.View
        route = RouteModel(Route())
        useCase.execute(RouteDetailsObserver(), viewCallback.getRouteId())
        viewCallback.setTabLayout()
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }

    @Suppress("UNUSED_PARAMETER")
    fun onPictureClick(view: View) {
        (viewCallback as RouteDetailsFragment).fragmentManager.beginTransaction()
                .replace(R.id.content_frame, routePictureFragment)
                .addToBackStack(null)
                .commit()
    }

    inner class RouteDetailsObserver : DefaultObserver<Route>() {
        override fun onNext(item: Route) {
            route = RouteModel(item)
            routePictureFragment.arguments = RoutePictureFragment.forPicture(route.imageLocation)
            notifyChange()
        }
    }
}