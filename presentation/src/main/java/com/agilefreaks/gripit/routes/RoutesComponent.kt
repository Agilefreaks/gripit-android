package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.ApplicationComponent
import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.repository.datasource.DataSourceModule
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(RoutesModule::class, DataSourceModule::class))
interface RoutesComponent {
    fun inject(routesActivity: RoutesActivity)

    fun listViewModel(): ListContract.ViewModel

    fun tabsViewModel(): TabsContract.ViewModel
}