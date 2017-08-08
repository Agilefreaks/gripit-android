package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabase
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class RouteGripDbDataStore @Inject constructor(val routeGripDatabase: RouteGripDatabase) : RouteGripDataStore {
    override fun addRouteGrip(routeGripEntity: RouteGripEntity): Observable<Unit> {
        return Observable.create { emitter ->
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val time = dateFormat.format(calendar.time)

            routeGripEntity.addDate = time

            routeGripDatabase.routeGripDao().addRouteGrip(routeGripEntity)

            emitter.onComplete()
        }
    }
}