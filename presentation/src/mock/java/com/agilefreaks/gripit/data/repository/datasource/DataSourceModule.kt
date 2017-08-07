package com.agilefreaks.gripit.data.repository.datasource

import android.arch.persistence.room.Room
import android.content.Context
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabaseMock
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides fun provideRouteDataStore(): RouteDataStore = MockRouteDataStore()

    @Provides fun provideRouteMeDataStore(): RouteMeDataStore = MockRouteMeDataStore()

    @Provides fun provideRouteMockGripDataStore(database: RouteGripDatabaseMock): RouteGripDataStore = MockRouteGripDataStore(database)

    @Provides fun provideRouteMemoryGripDatabase(context: Context): RouteGripDatabaseMock {
        return Room
                .databaseBuilder(context.applicationContext, RouteGripDatabaseMock::class.java, "grip_db_mock")
                .build()
    }

}
