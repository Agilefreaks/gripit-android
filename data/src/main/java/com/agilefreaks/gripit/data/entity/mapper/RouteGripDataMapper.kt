package com.agilefreaks.gripit.data.entity.mapper

import com.agilefreaks.gripit.data.entity.RouteGripEntity
import com.agilefreaks.gripit.domain.RouteGrip
import javax.inject.Inject

class RouteGripDataMapper
@Inject internal constructor() {

    fun transform(routeGripEntity: RouteGripEntity): RouteGrip {
        return RouteGrip(routeGripEntity.routeId, routeGripEntity.videoLocation, routeGripEntity.comments, routeGripEntity.gripped)
    }

    fun transform(routeGripEntityCollection: Collection<RouteGripEntity>): Collection<RouteGrip> {
        return routeGripEntityCollection.map { transform(it) }
    }

    fun transform(routeGrip: RouteGrip): RouteGripEntity {
        return RouteGripEntity(routeId = routeGrip.routeId,
                videoLocation = routeGrip.videoLocation,
                comments = routeGrip.comments,
                gripped = routeGrip.gripped
        )
    }
}