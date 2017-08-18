package com.agilefreaks.gripit.grip

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabaseMock
import com.agilefreaks.gripit.matchers.ConstraintLayoutHasBackgroundMatcher
import com.agilefreaks.gripit.matchers.ImageViewHasDrawableMatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.not
import org.hamcrest.core.StringStartsWith
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RouteGripActivityTest {
    @Rule
    @JvmField
    val activityGripRule: ActivityTestRule<RouteGripActivity> = ActivityTestRule(RouteGripActivity::class.java, false, false)
    val launchIntent = Intent()

    val gripToolbar: ViewInteraction by lazy { onView(withId(R.id.grip_toolbar)) }
    val videoLabel: ViewInteraction by lazy { onView(withId(R.id.video_label)) }
    val galleryIcon: ViewInteraction by lazy { onView(withId(R.id.gallery_icon)) }
    val videoThumbnail: ViewInteraction by lazy { onView(withId(R.id.video_thumbnail)) }
    val textComments: ViewInteraction by lazy { onView(withId(R.id.text_comments)) }
    val button: ViewInteraction by lazy { onView(withId(R.id.button)) }
    val routeMeCollection: ViewInteraction by lazy { onView(withId(R.id.route_me_collection)) }
    val routeId = 1

    @Before
    fun setup() {
        launchIntent.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
    }

    @After
    fun tearDown() {
        val database = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(), RouteGripDatabaseMock::class.java, "grip_db_mock").build()
        database.routeGripDao().clearTable()
    }

    @Test
    fun testThatAllAreDisplayed() {
        launchGripScreen()

        gripToolbar.check(matches(isDisplayed()))
        videoLabel.check(matches(withText("Video")))
        galleryIcon.check(matches(isDisplayed()))
        videoThumbnail.check(matches(isDisplayed()))
        textComments.check(matches(isDisplayed()))
        button.check(matches(isDisplayed()))
    }

    @Test
    fun testThatGripScreenIsDisplayed() {
        launchGripScreen()

        gripToolbar.check(matches(hasDescendant(withText(RouteState.GripIt.toString() + " Screen"))))
        button.check(matches(withText(RouteState.GripIt.toString())))
    }

    @Test
    fun testThatTryScreenIsDisplayed() {
        launchTryScreen()

        gripToolbar.check(matches(hasDescendant(withText(RouteState.TryIt.toString() + " Screen"))))
        button.check(matches(withText(RouteState.TryIt.toString())))
    }

    @Test
    fun testThatTryIsSavedAndDisplayed() {
        launchTryScreen()

        button.perform(click())

        onView(CoreMatchers.allOf(withText("Route Me"), isDescendantOfA(withId(R.id.tabs_route_details)))).perform(click())

        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_thumbnail), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_play), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_date), withText(StringStartsWith("Today"))))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_view_layout), not(ConstraintLayoutHasBackgroundMatcher.hasBackground())))))
    }

    @Test
    fun testThatGripItIsSavedAndDisplayed() {
        launchGripScreen()

        button.perform(click())

        onView(CoreMatchers.allOf(withText("Route Me"), isDescendantOfA(withId(R.id.tabs_route_details)))).perform(click())

        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_thumbnail), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_play), ImageViewHasDrawableMatcher.hasDrawable()))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_date), withText(StringStartsWith("Today"))))))
        routeMeCollection.check(matches(hasDescendant(CoreMatchers.allOf(withId(R.id.route_me_view_layout), ConstraintLayoutHasBackgroundMatcher.hasBackground()))))
    }

    fun launchGripScreen() {
        launchIntent.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, RouteState.GripIt)
        activityGripRule.launchActivity(launchIntent)
    }

    fun launchTryScreen() {
        launchIntent.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, RouteState.TryIt)
        activityGripRule.launchActivity(launchIntent)
    }
}