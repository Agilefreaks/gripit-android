package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.ApplicationComponent
import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.data.repository.datasource.DataSourceModule
import com.agilefreaks.gripit.routes.RoutesModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(RouteDetailsModule::class, DataSourceModule::class))
interface RouteDetailsComponent {
    fun inject(routeDetailsActivity: RouteDetailsActivity)
}