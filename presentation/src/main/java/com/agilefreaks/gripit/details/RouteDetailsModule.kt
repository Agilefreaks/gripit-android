package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.domain.interactor.GetRoutesUseCase
import com.agilefreaks.gripit.domain.repository.RouteRepository
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.list.ListViewModel
import com.agilefreaks.gripit.routes.tabs.TabsContract
import com.agilefreaks.gripit.routes.tabs.TabsFragment
import dagger.Module
import dagger.Provides

@Module
class RouteDetailsModule {
    @Provides @PerActivity fun provideRouteDetailsViewModel(): RouteDetailsContract.ViewModel {
        return RouteDetailsViewModel()
    }

    @Provides fun provideRouteDetailsFragment(routeDetailsViewModel: RouteDetailsViewModel): RouteDetailsContract.View {
        return RouteDetailsFragment(routeDetailsViewModel)
    }
}
