package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.details.info.RouteInfoContract
import com.agilefreaks.gripit.details.info.RouteInfoFragment
import com.agilefreaks.gripit.details.info.RouteInfoViewModel
import com.agilefreaks.gripit.details.me.RouteMeAdapter
import com.agilefreaks.gripit.details.me.RouteMeFragment
import com.agilefreaks.gripit.details.me.RouteMeViewModel
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import com.agilefreaks.gripit.details.picture.RoutePictureViewModel
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import com.agilefreaks.gripit.domain.repository.RouteRepository
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {
    @Provides @PerActivity fun provideRouteDetailsViewModel(getRouteDetails: GetRouteDetails): RouteDetailsContract.ViewModel {
        return RouteDetailsViewModel(getRouteDetails)
    }

    @Provides @PerActivity fun provideRouteInfoViewModel(getRouteDetails: GetRouteDetails): RouteInfoContract.ViewModel {
        return RouteInfoViewModel(getRouteDetails)
    }

    @Provides @PerActivity fun provideRoutePictureViewModel(): RoutePictureViewModel {
        return RoutePictureViewModel()
    }

    @Provides @PerActivity fun provideRouteMeViewModel(): RouteMeViewModel {
        return RouteMeViewModel()
    }

    @Provides fun provideRouteMeAdapter(): RouteMeAdapter {
        return RouteMeAdapter()
    }

    @Provides fun provideRouteDetailsFragment(routeDetailsViewModel: RouteDetailsViewModel): RouteDetailsContract.View {
        return RouteDetailsFragment.build(routeDetailsViewModel)
    }

    @Provides fun provideRoutePictureFragment(routePictureViewModel: RoutePictureViewModel): RoutePictureFragment {
        return RoutePictureFragment.build(routePictureViewModel)
    }

    @Provides fun provideRouteMeFragment(routeMeViewModel: RouteMeViewModel, routeMeAdapter: RouteMeAdapter): RouteMeFragment {
        return RouteMeFragment.build(routeMeViewModel, routeMeAdapter)
    }

    @Provides fun provideRouteRepository(routeDataRepository: RouteDataRepository): RouteRepository {
        return routeDataRepository
    }

    @Provides fun provideRouteInfoFragment(routeInfoViewModel: RouteInfoViewModel): RouteInfoFragment {
        return RouteInfoFragment.build(routeInfoViewModel)
    }
}
