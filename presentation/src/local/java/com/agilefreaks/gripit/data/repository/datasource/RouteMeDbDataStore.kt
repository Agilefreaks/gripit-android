package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabase
import io.reactivex.Observable
import javax.inject.Inject

class RouteMeDbDataStore @Inject constructor(val routeGripDatabase: RouteGripDatabase) : RouteMeDataStore {
    override fun routeMeEntities(routeId: Int): Observable<List<RouteGripEntity>> {

        return routeGripDatabase.routeGripDao().getGrips(routeId).toObservable()
    }
}