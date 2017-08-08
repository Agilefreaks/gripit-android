package com.agilefreaks.gripit.data.entity.mapper

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.domain.RouteMe
import javax.inject.Inject


class RouteMeEntityDataMapper @Inject internal constructor() {
    fun transform(routeGripEntity: RouteGripEntity): RouteMe {
        return RouteMe(routeGripEntity.videoLocation, routeGripEntity.addDate, routeGripEntity.gripped)
    }

    internal fun transform(routeGripEntityCollection: Collection<RouteGripEntity>): Collection<RouteMe> {
        val routeEntity = routeGripEntityCollection.map { transform(it) }
        return routeEntity
    }
}