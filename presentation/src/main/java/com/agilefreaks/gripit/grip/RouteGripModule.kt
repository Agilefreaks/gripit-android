package com.agilefreaks.gripit.grip

import com.agilefreaks.gripit.core.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class RouteGripModule{
    @Provides @PerActivity fun provideRouteGripViewModel(): RouteGripViewModel {
        return RouteGripViewModel()
    }

    @Provides fun provideRouteGripFragment(routeDetailsViewModel: RouteGripViewModel): RouteGripFragment {
        return RouteGripFragment.build(routeDetailsViewModel)
    }

}