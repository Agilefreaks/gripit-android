package com.agilefreaks.gripit.core.util

import android.support.test.espresso.IdlingResource


class EspressoIdlingResource {

    companion object {

        private val RESOURCE = "GLOBAL"

        private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

        val idlingResource: IdlingResource
            get() = mCountingIdlingResource
    }
}
