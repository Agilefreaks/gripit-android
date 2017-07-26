package com.agilefreaks.gripit.data.entity.mapper

import com.agilefreaks.gripit.data.entity.RouteMeEntity
import com.agilefreaks.gripit.domain.RouteMe
import javax.inject.Inject

/**
 * Created by Mihai on 26.07.2017.
 */
class RouteMeEntityDataMapper @Inject internal constructor() {

    fun transform(routeMeEntity: RouteMeEntity): RouteMe {
        return RouteMe(routeMeEntity.imageLocation, routeMeEntity.dateAdded)
    }

    fun transform(routeMeEntityCollection: Collection<RouteMeEntity>): Collection<RouteMe> {
        val routeEntity = routeMeEntityCollection.map { transform(it) }
        return routeEntity
    }
}