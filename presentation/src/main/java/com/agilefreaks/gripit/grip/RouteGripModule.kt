package com.agilefreaks.gripit.grip

import android.content.Context
import com.agilefreaks.gripit.core.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class RouteGripModule{
    @Provides @PerActivity fun provideRouteGripViewModel(context: Context): RouteGripViewModel {
        return RouteGripViewModel(context)
    }

    @Provides fun provideRouteGripFragment(routeDetailsViewModel: RouteGripViewModel): RouteGripFragment {
        return RouteGripFragment.build(routeDetailsViewModel)
    }

}