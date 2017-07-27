package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteMeEntity
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class RouteMeMemoryDataStore : RouteMeDataStore {

    override fun routeMeEntities(routeId: Int): Observable<Collection<RouteMeEntity>> {
        val random = Random()
        return Observable.fromArray((0..6).map{ RouteMeEntity(routeId, "placeholder.jpg", getDate(it), random.nextBoolean()) })
    }

    private fun getDate(it: Int): String {
        val calendar = GregorianCalendar.getInstance()
        val random = Random()

        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, -it)
        calendar.set(Calendar.HOUR, random.nextInt(23))
        calendar.set(Calendar.MINUTE, random.nextInt(59))

        return SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calendar.time)
    }
}