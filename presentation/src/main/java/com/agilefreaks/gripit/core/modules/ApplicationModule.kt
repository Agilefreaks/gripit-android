package com.agilefreaks.gripit.core.modules

import android.content.Context
import android.content.res.AssetManager
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.core.navigation.Navigator
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton internal fun provideApplicationContext(): Context = this.application

    @Provides
    @Singleton internal fun providesAssetManager(context: Context): AssetManager = context.assets

    @Provides
    @Singleton internal fun provideNavigator(applicationContext: Context): Navigator =
            Navigator(applicationContext)

    @Provides
    @Named("executionScheduler") internal fun provideExecutionScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("postExecutionScheduler") internal fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
