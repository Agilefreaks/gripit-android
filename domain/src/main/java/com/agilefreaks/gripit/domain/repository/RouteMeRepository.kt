package com.agilefreaks.gripit.domain.repository

import com.agilefreaks.gripit.domain.RouteMe
import io.reactivex.Observable

interface RouteMeRepository {
    fun routeTries(routeId: Int) : Observable<Collection<RouteMe>>
}