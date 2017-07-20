package com.agilefreaks.gripit.domain

data class Route(val id: Int = 0,
                 val name: String = "No Name",
                 val imageLocation: String = "placeholder.jpg",
                 val grade: String = "",
                 val type: List<String> = listOf(""),
                 val addDate: String = "",
                 val routeSetter: String = "",
                 val notes: List<String> = listOf(""))