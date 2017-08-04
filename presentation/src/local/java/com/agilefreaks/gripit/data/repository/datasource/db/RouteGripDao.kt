package com.agilefreaks.gripit.data.repository.datasource.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.agilefreaks.gripit.data.entity.RouteGripEntity
import io.reactivex.Flowable


@Dao
interface RouteGripDao {

    @Query("Select * from route_grip")
    fun getGrips(): Flowable<List<RouteGripEntity>>

    @Insert
    fun addRouteGrip(routeGrip: RouteGripEntity)
}