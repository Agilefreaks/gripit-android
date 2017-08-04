package com.agilefreaks.gripit.grip

import android.content.Context
import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.entity.mapper.RouteEntityDataMapper
import com.agilefreaks.gripit.data.entity.mapper.RouteGripDataMapper
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.data.repository.RouteGripDataRepository
import com.agilefreaks.gripit.data.repository.datasource.RouteDataStore
import com.agilefreaks.gripit.data.repository.datasource.RouteGripDataStore
import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.interactor.AddRouteGrip
import com.agilefreaks.gripit.domain.repository.RouteGripRepository
import com.agilefreaks.gripit.domain.repository.RouteRepository
import dagger.Module
import dagger.Provides

@Module
class RouteGripModule{
    @Provides @PerActivity fun provideRouteGripViewModel(context: Context, useCase: AddRouteGrip): RouteGripViewModel {
        return RouteGripViewModel(context, useCase)
    }

    @Provides fun provideRouteGripFragment(routeDetailsViewModel: RouteGripViewModel): RouteGripFragment {
        return RouteGripFragment.build(routeDetailsViewModel)
    }

    @Provides fun provideRouteGripRepository(dataStore: RouteGripDataStore, entityDataMapper: RouteGripDataMapper): RouteGripRepository {
        return RouteGripDataRepository(dataStore, entityDataMapper)
    }
}