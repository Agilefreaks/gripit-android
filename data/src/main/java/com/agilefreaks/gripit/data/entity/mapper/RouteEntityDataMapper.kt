package com.agilefreaks.gripit.data.entity.mapper

import com.agilefreaks.gripit.data.entity.RouteEntity
import com.agilefreaks.gripit.domain.Route
import javax.inject.Inject

class RouteEntityDataMapper
@Inject internal constructor() {

    fun transform(routeEntity: RouteEntity): Route {
        return Route(routeEntity.id, routeEntity.name, routeEntity.imageLocation, routeEntity.grade, routeEntity.type, routeEntity.addDate, routeEntity.routeSetter)
    }

    fun transform(routeEntityCollection: Collection<RouteEntity>): Collection<Route> {
        val routeEntity = routeEntityCollection.map { transform(it) }
        return routeEntity
    }
}