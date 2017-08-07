package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabaseMock
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MockRouteGripDataStore @Inject constructor(val routeGripDatabaseMock: RouteGripDatabaseMock) : RouteGripDataStore {
    override fun addRouteGrip(routeGripEntity: RouteGripEntity): Observable<Unit> {
        return Observable.fromCallable {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val time = dateFormat.format(calendar.time)

            routeGripEntity.addDate = time

            routeGripDatabaseMock.routeGripDao().addRouteGrip(routeGripEntity)
        }
    }
}