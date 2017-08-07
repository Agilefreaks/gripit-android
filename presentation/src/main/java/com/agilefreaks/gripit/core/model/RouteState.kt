package com.agilefreaks.gripit.core.model

import java.io.Serializable

enum class RouteState : Serializable {
    GripIt {
        override fun toString(): String {
            return "Grip It"
        }
    },
    TryIt {
        override fun toString(): String {
            return "Try It"
        }
    }
}