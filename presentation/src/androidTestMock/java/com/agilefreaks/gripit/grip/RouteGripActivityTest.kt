package com.agilefreaks.gripit.grip

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RouteGripActivityTest {
    @Rule @JvmField val activityGripRule: ActivityTestRule<RouteGripActivity> = ActivityTestRule(RouteGripActivity::class.java, false, false)


    val gripToolbar: ViewInteraction by lazy { onView(withId(R.id.grip_toolbar)) }
    val videoLabel: ViewInteraction by lazy { onView(withId(R.id.video_label)) }
    val galleryIcon: ViewInteraction by lazy { onView(withId(R.id.gallery_icon)) }
    val videoThumbnail: ViewInteraction by lazy { onView(withId(R.id.video_thumbnail)) }
    val textComments: ViewInteraction by lazy { onView(withId(R.id.text_comments)) }
    val button: ViewInteraction by lazy { onView(withId(R.id.button)) }
    val routeId = 1

    @Before fun setup() {
        val intentToLaunch = Intent()
        intentToLaunch.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
        intentToLaunch.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, RouteState.GripIt)
        activityGripRule.launchActivity(intentToLaunch)
    }

    @Test fun testThatAllAreDisplayed() {
        gripToolbar.check(matches(isDisplayed()))
        videoLabel.check(matches(withText("Video")))
        galleryIcon.check(matches(isDisplayed()))
        videoThumbnail.check(matches(isDisplayed()))
        textComments.check(matches(isDisplayed()))
        button.check(matches(isDisplayed()))
    }
}