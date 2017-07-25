package com.agilefreaks.gripit.core.modules

import android.content.Context
import android.content.res.AssetManager
import com.agilefreaks.gripit.AndroidApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides @Singleton internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides @Singleton internal fun providesAssetManager(context: Context): AssetManager {
        return context.assets
    }

    @Provides @Named("executionScheduler") internal fun provideExecutionScheduler(): Scheduler = Schedulers.io()

    @Provides @Named("postExecutionScheduler") internal fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
