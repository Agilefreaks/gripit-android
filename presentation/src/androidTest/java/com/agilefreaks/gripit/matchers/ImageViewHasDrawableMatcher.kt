package com.agilefreaks.gripit.matchers

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description

object ImageViewHasDrawableMatcher {
    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has drawable")
            }

            public override fun matchesSafely(imageView: ImageView): Boolean {
                return imageView.drawable != null
            }
        }
    }
}
