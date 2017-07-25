package com.agilefreaks.gripit.data.repository.datasource

import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides fun provideRouteDataStore() : RouteDataStore = MockRouteDataStore()
}