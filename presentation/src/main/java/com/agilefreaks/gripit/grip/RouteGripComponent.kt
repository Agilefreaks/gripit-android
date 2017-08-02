package com.agilefreaks.gripit.grip

import com.agilefreaks.gripit.ApplicationComponent
import com.agilefreaks.gripit.core.di.PerActivity
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.data.repository.datasource.DataSourceModule
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(RouteGripModule::class, DataSourceModule::class))
interface RouteGripComponent {
    fun inject(routeGripActivity: RouteGripActivity)

    fun navigator(): Navigator
}