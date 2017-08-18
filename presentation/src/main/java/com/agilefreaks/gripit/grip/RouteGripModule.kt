package com.agilefreaks.gripit.grip

import android.content.Context
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.data.entity.mapper.RouteGripDataMapper
import com.agilefreaks.gripit.data.repository.RouteGripDataRepository
import com.agilefreaks.gripit.data.repository.datasource.RouteGripDataStore
import com.agilefreaks.gripit.domain.interactor.AddRouteGrip
import com.agilefreaks.gripit.domain.repository.RouteGripRepository
import dagger.Module
import dagger.Provides

@Module
class RouteGripModule {
    @Provides
    fun provideRouteGripViewModel(context: Context, useCase: AddRouteGrip, navigator: Navigator): RouteGripViewModel =
            RouteGripViewModel(context, useCase, navigator)

    @Provides
    fun provideRouteGripFragment(routeDetailsViewModel: RouteGripViewModel): RouteGripFragment =
            RouteGripFragment.build(routeDetailsViewModel)

    @Provides
    fun provideRouteGripRepository(dataStore: RouteGripDataStore, entityDataMapper: RouteGripDataMapper): RouteGripRepository =
            RouteGripDataRepository(dataStore, entityDataMapper)
}