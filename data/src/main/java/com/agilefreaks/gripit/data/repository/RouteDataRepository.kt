package com.agilefreaks.gripit.data.repository

import com.agilefreaks.gripit.data.entity.mapper.RouteEntityDataMapper
import com.agilefreaks.gripit.data.repository.datasource.RouteDataStore
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.Types
import com.agilefreaks.gripit.domain.repository.RouteRepository
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RouteDataRepository
@Inject constructor(val dataStore: RouteDataStore, val mapper: RouteEntityDataMapper) : RouteRepository {

    override fun routes(params: Any?): Observable<Collection<Route>> {
        val filter = params as String
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val routes: Observable<Collection<Route>> = dataStore.routeEntities().map {
            mapper.transform(it).sortedByDescending { route -> dateFormat.parse(route.addDate) }
        }
        return if (filter == Types.NoFilter.toString()) routes else routes.map { it.filter { it.type.contains(filter) } }
    }

    override fun route(routeId: Int): Observable<Route> {
        return dataStore.routeEntityDetails(routeId).map { mapper.transform(it) }
    }
}