package com.agilefreaks.gripit.core.model

enum class RouteColor(val value: Char) {
    Green('5'),
    Yellow('6'),
    Red('7'),
    Black('8');

    companion object {
        fun from(findValue: Char): RouteColor = RouteColor.values().find { it.value == findValue } ?: RouteColor.Green
    }
}