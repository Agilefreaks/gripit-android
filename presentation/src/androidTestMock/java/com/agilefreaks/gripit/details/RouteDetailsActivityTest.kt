package com.agilefreaks.gripit.details

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.*
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.grip.RouteGripActivity
import com.agilefreaks.gripit.matchers.ImageViewHasDrawableMatcher
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RouteDetailsActivityTest {
    @Rule
    @JvmField
    val activityDetailsRule: IntentsTestRule<RouteDetailsActivity> = IntentsTestRule(RouteDetailsActivity::class.java, false, false)

    val routeToolbar: ViewInteraction by lazy { onView(withId(R.id.appbar)) }
    val tabsView: ViewInteraction by lazy { onView(withId(R.id.tabs_route_details)) }
    val infoView: ViewInteraction by lazy { onView(withId(R.id.info_layout)) }
    val routeDetailPicture: ViewInteraction by lazy { onView(withId(R.id.route_detail_picture)) }
    val routeMeCollection: ViewInteraction by lazy { onView(withId(R.id.route_me_collection)) }
    val routeId = 1

    @Before
    fun setup() {
        val intentToLaunch = Intent()
        intentToLaunch.putExtra(RouteDetailsActivity.INTENT_EXTRA_PARAM_ROUTE_ID, routeId)
        activityDetailsRule.launchActivity(intentToLaunch)
    }

    @Test
    fun testThatDetailsArePresent() {
        routeToolbar.check(matches(hasDescendant(withText("Route 1"))))
        routeToolbar.check(selectedDescendantsMatch(withId(R.id.route_picture_header), ImageViewHasDrawableMatcher.hasDrawable()))
    }

    @Test
    fun thatTabsArePresent() {
        tabsView.check(matches(hasDescendant(withText("Info"))))
    }

    @Test
    fun testDetailsAreShown() {
        infoView.check(matches(hasDescendant(withText("6A"))))
        infoView.check(matches(hasDescendant(withText("Easy"))))
        infoView.check(matches(hasDescendant(withText("Mock Climber"))))
    }

    @Test
    fun testThatFullScreenImageWorks() {
        onView(withId(R.id.route_picture_header)).perform(click())

        routeDetailPicture.check(matches(ImageViewHasDrawableMatcher.hasDrawable()))
    }

    @Test
    fun testThatRouteMeIsPresent() {
        onView(allOf(withText("Route Me"), isDescendantOfA(withId(R.id.tabs_route_details)))).perform(click())

        routeMeCollection.check(matches(hasDescendant(allOf(withId(R.id.route_me_thumbnail), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(allOf(withId(R.id.route_me_play), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(allOf(withId(R.id.route_me_date), withText("10/10/2010 13:00")))))
    }

    @Test
    fun testThatFabButtonIsDisplayed() {
        onView(withId(R.id.fab_expand_menu_button)).perform(click())

        onView(withId(R.id.grip_it)).check(matches(isDisplayed()))
        onView(withId(R.id.try_it)).check(matches(isDisplayed()))
    }

    @Test
    fun testThatGripScreenOpens() {
        val intent = Intent()
        val intentResult = ActivityResult(Activity.RESULT_OK, intent)

        intending(anyIntent()).respondWith(intentResult)

        onView(withId(R.id.fab_expand_menu_button)).perform(click())
        onView(withId(R.id.grip_it)).perform(click())

        intended(allOf(hasComponent(RouteGripActivity::class.java.name),
                hasExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId),
                hasExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, RouteState.GripIt)))
    }

    @Test
    fun testThatTryScreenOpens() {
        val intent = Intent()
        val intentResult = ActivityResult(Activity.RESULT_OK, intent)

        intending(anyIntent()).respondWith(intentResult)

        onView(withId(R.id.fab_expand_menu_button)).perform(click())
        onView(withId(R.id.try_it)).perform(click())

        intended(allOf(hasComponent(RouteGripActivity::class.java.name),
                hasExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId),
                hasExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, RouteState.TryIt)))
    }
}