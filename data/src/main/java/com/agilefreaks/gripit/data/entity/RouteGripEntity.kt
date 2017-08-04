package com.agilefreaks.gripit.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "route_grip")
data class RouteGripEntity constructor(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var routeId: Int = -1,
        var videoLocation: String = "",
        var comments: String = "",
        var gripped: Boolean = false,
        var addDate: String = ""
)