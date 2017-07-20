package com.agilefreaks.gripit.data.repository.datasource

import android.content.res.AssetManager
import com.agilefreaks.gripit.data.Serializer
import com.agilefreaks.gripit.data.entity.RouteEntity
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import javax.inject.Inject

class DiskRouteDataStore @Inject constructor(var serializer: Serializer, var assetManager: AssetManager) : RouteDataStore {

    private val DEFAULT_FILE_NAME = "route_list.json"

    override fun routeEntities(): Observable<Collection<RouteEntity>> {
        return Observable.create { emitter ->
            val routesJson = assetManager.open(DEFAULT_FILE_NAME).bufferedReader().use { it.readText() }
            try {

                val routes = serializer.deserialize(routesJson, object : TypeToken<Collection<RouteEntity>>() {})

                emitter.onNext(routes)
                emitter.onComplete()
            } catch(ex: Exception) {
                emitter.onError(ex)

            }

        }
    }

    override fun routeEntityDetails(routeId: Int): Observable<RouteEntity> {
        return Observable.create { emitter ->
            val routesJson = assetManager.open(DEFAULT_FILE_NAME).bufferedReader().use { it.readText() }
            val routes = serializer.deserialize(routesJson, object : TypeToken<Collection<RouteEntity>>() {})

            emitter.onNext(routes.first { it.id == routeId })
            emitter.onComplete()
        }
    }
}