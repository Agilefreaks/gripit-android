package com.agilefreaks.gripit.data.repository.datasource

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.data.entity.RouteMeEntity
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class RouteMeMemoryDataStoreTest {
    private val routeMeDataStore = RouteMeMemoryDataStore()

    @Test fun routeMeEntities() {
        val expectedEntity = RouteMeEntity(0,"placeholder.jpg")

        val entities = routeMeDataStore.routeMeEntities(expectedEntity.routeId).test().values()[0].toList()

        MatcherAssert.assertThat(entities[0].routeId, CoreMatchers.`is`(expectedEntity.routeId))
        MatcherAssert.assertThat(entities[0].imageLocation, CoreMatchers.`is`(expectedEntity.imageLocation))
    }
}