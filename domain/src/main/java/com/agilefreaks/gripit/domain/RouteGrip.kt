package com.agilefreaks.gripit.domain


data class RouteGrip constructor(val routeId: Int = -1,
                                 val videoLocation: String = "",
                                 val comments: String = "",
                                 val gripped: Boolean = false)