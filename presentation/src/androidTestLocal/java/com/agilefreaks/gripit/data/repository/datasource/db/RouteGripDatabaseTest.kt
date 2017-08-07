package com.agilefreaks.gripit.data.repository.datasource.db

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.data.entity.RouteGripEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class RouteGripDatabaseTest {
    lateinit var routeGripDataBase: RouteGripDatabase
    lateinit var routeGripDao: RouteGripDao

    @Before
    fun setup() {
        routeGripDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), RouteGripDatabase::class.java).build()
        routeGripDao = routeGripDataBase.routeGripDao()
    }

    @After
    fun cleanUp() {
        routeGripDataBase.close()
    }

    @Test
    fun testThatFetchingWorks() {
        val expectedEntityFirst = RouteGripEntity(routeId = 1, comments = "Test", videoLocation = "test.jpg")
        val expectedEntitySecond = RouteGripEntity(routeId = 2, comments = "Test", videoLocation = "test.jpg")
        routeGripDao.addRouteGrip(expectedEntityFirst)
        routeGripDao.addRouteGrip(expectedEntitySecond)

        val values = routeGripDao.getGrips().blockingFirst()

        MatcherAssert.assertThat(values.count(), CoreMatchers.equalTo(2))
    }

    @Test
    fun itAddsAGripToTheDataBaseTest() {
        val expectedEntity = RouteGripEntity(routeId = 0, comments = "Test", videoLocation = "test.jpg")
        routeGripDao.addRouteGrip(expectedEntity)

        val values = routeGripDao.getGrips(expectedEntity.routeId).blockingFirst()

        MatcherAssert.assertThat(values[0].routeId, CoreMatchers.equalTo(expectedEntity.routeId))
        MatcherAssert.assertThat(values[0].comments, CoreMatchers.equalTo(expectedEntity.comments))
    }
}