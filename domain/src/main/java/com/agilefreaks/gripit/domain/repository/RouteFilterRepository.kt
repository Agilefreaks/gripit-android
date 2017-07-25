package com.agilefreaks.gripit.domain.repository

import com.agilefreaks.gripit.domain.RouteFilter
import io.reactivex.Observable


interface RouteFilterRepository {
    fun filters(): Observable<Collection<RouteFilter>>
}