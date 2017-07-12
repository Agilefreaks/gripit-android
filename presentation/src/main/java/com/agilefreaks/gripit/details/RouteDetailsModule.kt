package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.domain.repository.RouteRepository
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {

    @Provides fun provideRouteRepository(routeDataRepository: RouteDataRepository): RouteRepository {
        return routeDataRepository
    }
}
