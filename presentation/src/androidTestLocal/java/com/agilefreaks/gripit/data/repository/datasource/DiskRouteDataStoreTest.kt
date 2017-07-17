package com.agilefreaks.gripit.data.repository.datasource

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.agilefreaks.gripit.data.Serializer
import com.agilefreaks.gripit.data.entity.RouteEntity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DiskRouteDataStoreTest {
    private val diskRouteDataSource = DiskRouteDataStore(Serializer(), InstrumentationRegistry.getContext().assets)

    @Test
    fun routeEntitiesReturnsAnObservableCollectionOfRoutes() {
        val expectedEntity = RouteEntity(0, "Route disk 1", "placeholder.jpg", "1", listOf("Great", "Easy"), "June 2018", "Climber")

        diskRouteDataSource.routeEntities().test().assertValue({ it.size == 3 })
        diskRouteDataSource.routeEntities().test().assertValueAt(0, { it.contains(expectedEntity) })

        val entities = diskRouteDataSource.routeEntities().test().values()[0].toList()
        assertThat(entities[0].id, `is`(expectedEntity.id))
        assertThat(entities[0].imageLocation, `is`(expectedEntity.imageLocation))
        assertThat(entities[0].type, `is`(expectedEntity.type))
    }

    @Test
    fun routeEntitiesReturnsAnObservableRoute() {
        val expectedEntity = RouteEntity(0, "Route disk 1", "placeholder.jpg", "1", listOf("Great", "Easy"), "June 2018", "Climber")

        diskRouteDataSource.routeEntityDetails(0).test().assertValue { it.id == 0 }

        val entity = diskRouteDataSource.routeEntityDetails(0).test().values()[0]
        assertThat(entity.imageLocation, `is`(expectedEntity.imageLocation))
        assertThat(entity.type, `is`(expectedEntity.type))
    }
}