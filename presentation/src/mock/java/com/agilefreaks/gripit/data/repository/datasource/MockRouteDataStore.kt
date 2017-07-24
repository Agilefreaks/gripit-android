package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteEntity
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class MockRouteDataStore : RouteDataStore {
    override fun routeEntities(): Observable<Collection<RouteEntity>> {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val time = dateFormat.format(calendar.time)
        return Observable.fromArray(listOf(
                RouteEntity(1, "Route 1", "placeholder.jpg", "6A", listOf("Easy"), time, "Mock Climber", listOf("")),
                RouteEntity(2, "Route 2", "placeholder.jpg", "6A", listOf("Medium"), time, "Mock Climber", listOf("")),
                RouteEntity(3, "Route 3", "placeholder.jpg", "6A", listOf("Hard"), time, "Mock Climber", listOf(""))))
    }

    override fun routeEntityDetails(routeId: Int): Observable<RouteEntity> {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val time = dateFormat.format(calendar.time)
        return Observable.just(RouteEntity(1, "Route 1", "placeholder.jpg", "6A", listOf("Easy"), time, "Mock Climber", listOf("")))
    }
}