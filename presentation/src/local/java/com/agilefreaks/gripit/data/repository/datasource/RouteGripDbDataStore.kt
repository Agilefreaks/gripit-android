package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabase
import io.reactivex.Observable
import javax.inject.Inject


class RouteGripDbDataStore @Inject constructor(val routeGripDatabase: RouteGripDatabase) : RouteGripDataStore {
    override fun addRouteGrip(routeGripEntity: RouteGripEntity): Observable<Unit> {
        return Observable.fromCallable {
            routeGripDatabase.routeGripDao().addRouteGrip(routeGripEntity)
        }
    }
}