package com.agilefreaks.gripit.data.repository

import com.agilefreaks.gripit.data.entity.mapper.RouteMeEntityDataMapper
import com.agilefreaks.gripit.data.repository.datasource.RouteMeDataStore
import com.agilefreaks.gripit.domain.RouteMe
import com.agilefreaks.gripit.domain.repository.RouteMeRepository
import io.reactivex.Observable
import javax.inject.Inject

class RouteMeDataRepository
@Inject constructor(val dataStore: RouteMeDataStore, val mapper: RouteMeEntityDataMapper) : RouteMeRepository {
    override fun routeTries(routeId: Int): Observable<Collection<RouteMe>> {
        return Observable.empty()
    }
}