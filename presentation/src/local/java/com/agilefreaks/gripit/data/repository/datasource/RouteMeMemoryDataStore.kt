package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteMeEntity
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class RouteMeMemoryDataStore : RouteMeDataStore {

    override fun routeMeEntities(): Observable<Collection<RouteMeEntity>> {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val time = dateFormat.format(calendar.time)

        android.util.Log.wtf("TIME:",time)

        return Observable.fromArray(listOf(RouteMeEntity(0, "placeholder.jpg", time),
                RouteMeEntity(0, "placeholder.jpg", "/06/2017 15:00"),
                RouteMeEntity(1, "placeholder.jpg", "05/06/2017 15:00"),
                RouteMeEntity(1, "placeholder.jpg", "05/06/2017 15:00"),
                RouteMeEntity(3, "placeholder.jpg", "05/06/2017 15:00")
        ))

    }
}