package com.agilefreaks.gripit.data.repository.datasource

import android.arch.persistence.room.Room
import android.content.Context
import android.content.res.AssetManager
import com.agilefreaks.gripit.data.Serializer
import com.agilefreaks.gripit.data.repository.datasource.db.RouteGripDatabase
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides fun provideRouteDataStore(serializer: Serializer, assetManager: AssetManager): RouteDataStore = DiskRouteDataStore(serializer, assetManager)

    @Provides fun provideRouteMeMemoryDataStore(routeGripDatabase: RouteGripDatabase): RouteMeDataStore = RouteMeDbDataStore(routeGripDatabase)

    @Provides fun provideRouteGripDbDataStore(routeGripDatabase: RouteGripDatabase): RouteGripDataStore = RouteGripDbDataStore(routeGripDatabase)

    @Provides fun provideRouteGripDatabase(context: Context): RouteGripDatabase {
        return Room
                .databaseBuilder(context.applicationContext, RouteGripDatabase::class.java, "grip_db")
                .build()
    }
}