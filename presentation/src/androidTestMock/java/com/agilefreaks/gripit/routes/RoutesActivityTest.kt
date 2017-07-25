package com.agilefreaks.gripit.routes

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.matchers.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoutesActivityTest {
    @Rule @JvmField val activityRule: ActivityTestRule<RoutesActivity> = ActivityTestRule(RoutesActivity::class.java)

    val routeCollectionView: ViewInteraction by lazy { onView(withId(R.id.route_collection)) }
    val tabsView: ViewInteraction by lazy { onView(withId(R.id.tabs)) }

    @Test fun testThatRoutesAreListed() {
        routeCollectionView.check(matches(hasDescendant(withText("Route 1"))))
        routeCollectionView.check(matches(hasDescendant(withText("Route 2"))))
        routeCollectionView.check(matches(hasDescendant(withText("Route 3"))))
        routeCollectionView.check(selectedDescendantsMatch(withId(R.id.route_image), hasDrawable()))
        routeCollectionView.check(matches(hasDescendant(withText("6A"))))
    }

    @Test fun testThatTabsAreListed() {
        tabsView.check(matches(hasDescendant(withText("No Filter"))))
        tabsView.check(matches(hasDescendant(withText("Easy"))))
        tabsView.check(matches(hasDescendant(withText("Hard"))))
    }

    @Test fun testThatFilteringWorks() {
        onView(allOf(withText("Easy"), isDescendantOfA(withId(R.id.tabs)))).perform(click())

        routeCollectionView.check(matches(hasDescendant(withText("Route 1"))))
        routeCollectionView.check(selectedDescendantsMatch(withId(R.id.route_image), hasDrawable()))
    }
}