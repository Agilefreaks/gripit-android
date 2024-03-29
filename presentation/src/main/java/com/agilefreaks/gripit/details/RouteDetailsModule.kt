package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.data.entity.mapper.RouteEntityDataMapper
import com.agilefreaks.gripit.data.entity.mapper.RouteMeEntityDataMapper
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.data.repository.RouteMeDataRepository
import com.agilefreaks.gripit.data.repository.datasource.RouteDataStore
import com.agilefreaks.gripit.data.repository.datasource.RouteMeDataStore
import com.agilefreaks.gripit.details.info.RouteInfoContract
import com.agilefreaks.gripit.details.info.RouteInfoFragment
import com.agilefreaks.gripit.details.info.RouteInfoViewModel
import com.agilefreaks.gripit.details.me.RouteMeAdapter
import com.agilefreaks.gripit.details.me.RouteMeFragment
import com.agilefreaks.gripit.details.me.RouteMeViewModel
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import com.agilefreaks.gripit.details.picture.RoutePictureViewModel
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import com.agilefreaks.gripit.domain.interactor.GetRouteMe
import com.agilefreaks.gripit.domain.repository.RouteMeRepository
import com.agilefreaks.gripit.domain.repository.RouteRepository
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {
    @Provides
    @PerActivity
    fun provideRouteDetailsViewModel(getRouteDetails: GetRouteDetails): RouteDetailsContract.ViewModel =
            RouteDetailsViewModel(getRouteDetails)

    @Provides
    @PerActivity
    fun provideRouteInfoViewModel(getRouteDetails: GetRouteDetails): RouteInfoContract.ViewModel =
            RouteInfoViewModel(getRouteDetails)

    @Provides
    @PerActivity
    fun provideRoutePictureViewModel(): RoutePictureViewModel = RoutePictureViewModel()

    @Provides
    @PerActivity
    fun provideRouteMeViewModel(useCase: GetRouteMe, navigator: Navigator): RouteMeViewModel =
            RouteMeViewModel(useCase, navigator)

    @Provides
    fun provideRouteMeAdapter(routeMeViewModel: RouteMeViewModel): RouteMeAdapter =
            RouteMeAdapter(routeMeViewModel)

    @Provides
    fun provideRouteDetailsFragment(routeDetailsViewModel: RouteDetailsViewModel): RouteDetailsContract.View =
            RouteDetailsFragment.build(routeDetailsViewModel)

    @Provides
    fun provideRoutePictureFragment(routePictureViewModel: RoutePictureViewModel): RoutePictureFragment =
            RoutePictureFragment.build(routePictureViewModel)

    @Provides
    fun provideRouteMeFragment(routeMeViewModel: RouteMeViewModel, routeMeAdapter: RouteMeAdapter): RouteMeFragment =
            RouteMeFragment.build(routeMeViewModel, routeMeAdapter)

    @Provides
    fun provideRouteRepository(dataStore: RouteDataStore, entityDataMapper: RouteEntityDataMapper): RouteRepository =
            RouteDataRepository(dataStore, entityDataMapper)

    @Provides
    fun provideRouteMeRepository(dataStore: RouteMeDataStore, entityDataMapper: RouteMeEntityDataMapper): RouteMeRepository =
            RouteMeDataRepository(dataStore, entityDataMapper)

    @Provides
    fun provideRouteInfoFragment(routeInfoViewModel: RouteInfoViewModel): RouteInfoFragment =
            RouteInfoFragment.build(routeInfoViewModel)
}
