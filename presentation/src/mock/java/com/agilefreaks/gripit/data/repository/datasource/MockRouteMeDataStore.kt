package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabaseMock
import io.reactivex.Observable

class MockRouteMeDataStore constructor(val database: RouteGripDatabaseMock) : RouteMeDataStore {
    override fun routeMeEntities(routeId: Int): Observable<List<RouteGripEntity>> =
            database.routeGripDao().getGrips(routeId).toObservable().map {
                it + listOf(RouteGripEntity(routeId = 1, videoLocation = "file:///android_asset/placeholder.jpg", addDate = "10/10/2010 13:00", gripped = false)) }
}