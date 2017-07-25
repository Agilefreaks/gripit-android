package com.agilefreaks.gripit.core


interface Lifecycle {
    interface View

    interface ViewModel {
        fun onViewResume()
        fun onViewAttached(viewCallback: Lifecycle.View)
        fun onViewDetached()
        fun onViewPaused()
    }
}
