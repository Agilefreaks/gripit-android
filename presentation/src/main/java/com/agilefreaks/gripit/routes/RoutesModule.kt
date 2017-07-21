package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.data.repository.RouteDataRepository
import com.agilefreaks.gripit.data.repository.RouteFilterDataRepository
import com.agilefreaks.gripit.domain.interactor.GetFilter
import com.agilefreaks.gripit.domain.interactor.GetRoutes
import com.agilefreaks.gripit.domain.repository.RouteFilterRepository
import com.agilefreaks.gripit.domain.repository.RouteRepository
import com.agilefreaks.gripit.routes.list.ListAdapter
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.list.ListFragment
import com.agilefreaks.gripit.routes.list.ListViewModel
import com.agilefreaks.gripit.routes.tabs.TabsContract
import com.agilefreaks.gripit.routes.tabs.TabsFragment
import com.agilefreaks.gripit.routes.tabs.TabsViewModel
import dagger.Module
import dagger.Provides

@Module
class RoutesModule {
    @Provides @PerActivity fun provideListViewModel(getRoutesUseCase: GetRoutes): ListContract.ViewModel {
        return ListViewModel(getRoutesUseCase)
    }

    @Provides @PerActivity fun provideTabsViewModel(getFilter: GetFilter): TabsContract.ViewModel {
        return TabsViewModel(getFilter)
    }

    @Provides @PerActivity fun provideRouteController(listViewModel: ListContract.ViewModel, tabsViewModel: TabsContract.ViewModel, navigator: Navigator): RoutesController {
        return RoutesController(listViewModel, tabsViewModel, navigator)
    }

    @Provides fun providesTabsView(tabsViewModel: TabsContract.ViewModel): TabsContract.View {
        return TabsFragment.build(tabsViewModel)
    }

    @Provides fun providesListView(listViewModel: ListContract.ViewModel, listAdapter: ListAdapter): ListContract.View {
        return ListFragment.build(listViewModel, listAdapter)
    }

    @Provides fun provideRouteRepository(routeDataRepository: RouteDataRepository): RouteRepository {
        return routeDataRepository
    }

    @Provides fun provideRouteFilterRepository(routeFilterDataRepository: RouteFilterDataRepository): RouteFilterRepository {
        return routeFilterDataRepository
    }
}
