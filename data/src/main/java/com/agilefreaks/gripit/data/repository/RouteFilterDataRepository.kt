package com.agilefreaks.gripit.data.repository

import com.agilefreaks.gripit.data.entity.mapper.RouteFilterDataMapper
import com.agilefreaks.gripit.data.repository.datasource.RouteDataStore
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.domain.repository.RouteFilterRepository
import io.reactivex.Observable
import javax.inject.Inject

class RouteFilterDataRepository
@Inject constructor(val dataStore: RouteDataStore, val mapper: RouteFilterDataMapper) : RouteFilterRepository {
    override fun filters(): Observable<Collection<RouteFilter>> {
        return dataStore.routeEntities().map { mapper.transform(it) }
    }
}