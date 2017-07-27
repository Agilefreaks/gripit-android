package com.agilefreaks.gripit.data.repository.datasource

import android.content.res.AssetManager
import com.agilefreaks.gripit.data.Serializer
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides fun provideRouteDataStore(serializer: Serializer, assetManager: AssetManager): RouteDataStore = DiskRouteDataStore(serializer, assetManager)

    @Provides fun provideRouteMeMemoryDataStore(): RouteMeDataStore = RouteMeMemoryDataStore()
}