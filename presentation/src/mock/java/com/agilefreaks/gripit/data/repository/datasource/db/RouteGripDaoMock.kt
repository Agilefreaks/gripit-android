package com.agilefreaks.gripit.data.repository.datasource.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.agilefreaks.gripit.data.entity.RouteGripEntity
import io.reactivex.Flowable


@Dao
interface RouteGripDaoMock {

    @Query("Select * from route_grip")
    fun getGrips(): Flowable<List<RouteGripEntity>>

    @Query("Select * from route_grip WHERE routeId = :routeId")
    fun getGrips(routeId: Int): Flowable<List<RouteGripEntity>>

    @Insert
    fun addRouteGrip(routeGrip: RouteGripEntity)

    @Query("Delete from route_grip")
    fun clearTable()
}