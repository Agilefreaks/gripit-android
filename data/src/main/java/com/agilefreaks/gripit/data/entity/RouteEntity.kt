package com.agilefreaks.gripit.data.entity

data class RouteEntity(val id: Int = 0,
                       val name: String = "No Name",
                       val imageLocation: String = "",
                       val grade: String = "",
                       val type: List<String> = listOf(""),
                       val addDate: String = "",
                       val routeSetter: String = "",
                       val notes: List<String> = listOf(""))
