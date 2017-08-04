package com.agilefreaks.gripit.core.model

enum class RouteState {
    GripIt{
        override fun toString(): String {
            return "Grip It"
        }
    },
    TryIt
    {
        override fun toString(): String {
            return "Try It"
        }
    }
}