package com.agilefreaks.gripit.domain.repository

import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.RouteMe
import io.reactivex.Observable

interface RouteGripRepository {
    fun addRouteGrip(routeGrip: RouteGrip) : Observable<Unit>
}