package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import io.reactivex.Observable

interface RouteMeDataStore {
    fun routeMeEntities(routeId: Int): Observable<List<RouteGripEntity>>
}
