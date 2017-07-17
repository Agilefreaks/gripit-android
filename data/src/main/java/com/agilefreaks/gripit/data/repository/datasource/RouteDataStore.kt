package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteEntity
import io.reactivex.Observable

interface RouteDataStore {
    fun routeEntities(): Observable<Collection<RouteEntity>>

    fun routeEntityDetails(routeId: Int): Observable<RouteEntity>
}
