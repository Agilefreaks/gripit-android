package com.agilefreaks.gripit.details.details

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.details.RouteDetailsActivity
import com.agilefreaks.gripit.matchers.ImageViewHasDrawableMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RouteDetailsActivityTest {
    @Rule @JvmField val activityDetailsRule: ActivityTestRule<RouteDetailsActivity> = ActivityTestRule(RouteDetailsActivity::class.java, false, false)

    val routeToolbar: ViewInteraction by lazy { onView(withId(R.id.appbar)) }
    val tabsView: ViewInteraction by lazy { onView(withId(R.id.tabs_route_details)) }
    val infoView: ViewInteraction by lazy { onView(withId(R.id.info_layout)) }
    val routeDetailPicture: ViewInteraction by lazy { onView(withId(R.id.route_detail_picture)) }
    val routeId = 1

    @Before fun setup() {
        val intentToLaunch = Intent()
        intentToLaunch.putExtra(RouteDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, routeId)
        activityDetailsRule.launchActivity(intentToLaunch)
    }


    @Test fun testThatDetailsArePresent() {
        routeToolbar.check(matches(hasDescendant(withText("Route 1"))))
        routeToolbar.check(selectedDescendantsMatch(withId(R.id.route_picture_header), ImageViewHasDrawableMatcher.hasDrawable()))
    }

    @Test fun thatTabsArePresent() {
        tabsView.check(matches(hasDescendant(withText("Info"))))
    }

    @Test fun testDetailsAreShown() {
        infoView.check(matches(hasDescendant(withText("6A"))))
        infoView.check(matches(hasDescendant(withText("Easy"))))
        infoView.check(matches(hasDescendant(withText("Mock Climber"))))
    }

    @Test fun testThatFullScreenImageWorks() {
        onView(withId(R.id.route_picture_header)).perform(click())

        routeDetailPicture.check(matches(ImageViewHasDrawableMatcher.hasDrawable()))

    }
}