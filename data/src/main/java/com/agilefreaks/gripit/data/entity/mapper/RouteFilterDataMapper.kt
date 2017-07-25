package com.agilefreaks.gripit.data.entity.mapper

import com.agilefreaks.gripit.data.entity.RouteEntity
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.domain.Types
import javax.inject.Inject

class RouteFilterDataMapper
@Inject internal constructor() {
    fun transform(routeEntity: RouteEntity): Collection<RouteFilter> {
        return routeEntity.type.map { RouteFilter(it) }
    }

    fun transform(routeFilterEntityCollection: Collection<RouteEntity>): Collection<RouteFilter> {
        val routes = listOf(RouteFilter(Types.NoFilter.toString())) + routeFilterEntityCollection.flatMap { transform(it) }
        return routes.toSet()
    }
}