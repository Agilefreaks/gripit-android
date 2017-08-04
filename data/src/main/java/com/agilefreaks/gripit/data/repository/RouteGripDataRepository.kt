package com.agilefreaks.gripit.data.repository

import com.agilefreaks.gripit.data.entity.mapper.RouteGripDataMapper
import com.agilefreaks.gripit.data.repository.datasource.RouteGripDataStore
import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.repository.RouteGripRepository
import io.reactivex.Observable
import javax.inject.Inject

class RouteGripDataRepository
@Inject constructor(val dataStore: RouteGripDataStore, val mapper: RouteGripDataMapper) : RouteGripRepository {
    override fun addRouteGrip(routeGrip: RouteGrip): Observable<Unit> {
        val entity = mapper.transform(routeGrip)
        return dataStore.addRouteGrip(entity)
    }
}