package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import io.reactivex.Observable

class MockRouteMeDataStore : RouteMeDataStore {
    override fun routeMeEntities(routeId: Int): Observable<List<RouteGripEntity>> {
        return Observable.fromArray(listOf(RouteGripEntity(routeId = 1, videoLocation = "file:///android_asset/placeholder.jpg", addDate = "10/10/2010 13:00", gripped = false)))
    }
}