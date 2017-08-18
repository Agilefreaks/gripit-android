package com.agilefreaks.gripit.matchers

import android.support.constraint.ConstraintLayout
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

object ConstraintLayoutHasBackgroundMatcher {
    fun hasBackground(): BoundedMatcher<View, ConstraintLayout> {
        return object : BoundedMatcher<View, ConstraintLayout>(ConstraintLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has drawable")
            }

            public override fun matchesSafely(view: ConstraintLayout): Boolean =
                    view.background != null
        }
    }
}