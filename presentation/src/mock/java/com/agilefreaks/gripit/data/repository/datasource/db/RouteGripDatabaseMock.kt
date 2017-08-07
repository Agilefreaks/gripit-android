package com.agilefreaks.gripit.data.repository.datasource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.agilefreaks.gripit.data.entity.RouteGripEntity

@Database(entities = arrayOf(RouteGripEntity::class), version = 1)
abstract class RouteGripDatabaseMock : RoomDatabase() {
    abstract fun routeGripDao(): RouteGripDaoMock
}