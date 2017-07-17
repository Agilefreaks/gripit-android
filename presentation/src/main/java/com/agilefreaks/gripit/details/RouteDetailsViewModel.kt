package com.agilefreaks.gripit.details

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class RouteDetailsViewModel @Inject constructor(val useCase: GetRouteDetails) : BaseObservable(), RouteDetailsContract.ViewModel {
    @Inject lateinit var routePictureFragment: RoutePictureFragment
    lateinit var viewCallback: RouteDetailsContract.View
    lateinit var route: Route

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteDetailsContract.View
        useCase.execute(RouteDetailsObserver(), viewCallback.getRouteId())
    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }

    inner class RouteDetailsObserver : DisposableObserver<Route>() {
        override fun onComplete() {
            // ignore
        }

        override fun onError(e: Throwable?) {
        }

        override fun onNext(newRoute: Route) {
            route = newRoute
            routePictureFragment.arguments = RoutePictureFragment.forPicture(route.imageLocation)
            viewCallback.setPictureNavigation(routePictureFragment)
        }
    }
}