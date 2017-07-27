package com.agilefreaks.gripit.data.repository.datasource

import com.agilefreaks.gripit.data.entity.RouteMeEntity
import io.reactivex.Observable

interface RouteMeDataStore {
    fun routeMeEntities(): Observable<Collection<RouteMeEntity>>
}