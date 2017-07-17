package com.agilefreaks.gripit.domain.repository

import com.agilefreaks.gripit.domain.Route
import io.reactivex.Observable

interface RouteRepository {
    fun routes(params: Any?): Observable<Collection<Route>>

    fun route(routeId: Int) : Observable<Route>
}
