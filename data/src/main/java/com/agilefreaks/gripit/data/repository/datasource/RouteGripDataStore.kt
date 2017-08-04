package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import io.reactivex.Observable

interface RouteGripDataStore {
    fun addRouteGrip(routeGripEntity: RouteGripEntity): Observable<Unit>
}