package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.details.picture.RoutePictureFragment
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {
    @Provides @PerActivity fun provideRouteDetailsViewModel(): RouteDetailsContract.ViewModel {
        return RouteDetailsViewModel()
    }

    @Provides fun provideRouteDetailsFragment(routeDetailsViewModel: RouteDetailsViewModel, navigator: Navigator): RouteDetailsContract.View {
        return RouteDetailsFragment(routeDetailsViewModel, navigator)
    }

    @Provides fun provideRoutePictureFragment(): RoutePictureFragment {
        return RoutePictureFragment()
    }
}
