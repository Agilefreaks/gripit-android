package com.agilefreaks.gripit.core.model


class RouteModel constructor(route: com.agilefreaks.gripit.domain.Route) {
    val name: String = route.name
    val imageLocation: String = route.imageLocation
    val grade: String = route.grade
    val type: List<String> = route.type
}