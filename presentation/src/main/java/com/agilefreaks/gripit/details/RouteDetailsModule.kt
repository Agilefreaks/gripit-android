package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import com.agilefreaks.gripit.domain.repository.RouteRepository
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {
    @Provides @PerActivity fun provideRouteDetailsViewModel(getRouteDetails: GetRouteDetails): RouteDetailsContract.ViewModel {
        return RouteDetailsViewModel(getRouteDetails)
    }

    @Provides fun provideRouteDetailsFragment(routeDetailsViewModel: RouteDetailsViewModel): RouteDetailsContract.View {
        return RouteDetailsFragment(routeDetailsViewModel)
    }

    @Provides fun provideRoutePictureFragment(): RoutePictureFragment {
        return RoutePictureFragment()
    }

    @Provides fun provideRouteRepository(routeDataRepository: RouteDataRepository): RouteRepository {
        return routeDataRepository
    }
}
