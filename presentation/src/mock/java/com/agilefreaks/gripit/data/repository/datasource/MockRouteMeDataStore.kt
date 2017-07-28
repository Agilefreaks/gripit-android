package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteMeEntity
import io.reactivex.Observable

class MockRouteMeDataStore : RouteMeDataStore {
    override fun routeMeEntities(routeId: Int): Observable<Collection<RouteMeEntity>> {
        return Observable.fromArray(listOf(RouteMeEntity(routeId, "placeholder.jpg", "10/10/2010 13:00", false)))
    }
}