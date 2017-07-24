package com.agilefreaks.gripit.details.info

import android.databinding.BaseObservable
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.interactor.DefaultObserver
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import javax.inject.Inject


class RouteInfoViewModel @Inject constructor(val useCase: GetRouteDetails) : BaseObservable(), RouteInfoContract.ViewModel {
    lateinit var viewCallback: RouteInfoContract.View
    var grade = ""
    var type = ""
    var addDate = ""
    var routeSetter = ""
    var notes = ""

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteInfoContract.View
        useCase.execute(RouteDetailsObserver(), viewCallback.getRouteId())
    }

    override fun onViewDetached() {
        useCase.dispose()
    }

    override fun onViewPaused() {
    }


    inner class RouteDetailsObserver : DefaultObserver<Route>() {
        override fun onNext(item: Route) {
            grade = item.grade
            type = item.type.joinToString()
            addDate = item.addDate
            routeSetter = item.routeSetter
            notes = item.notes.joinToString()
            notifyChange()
        }
    }
}