package com.agilefreaks.gripit

import android.content.Context
import android.content.res.AssetManager
import com.agilefreaks.gripit.core.modules.ApplicationModule
import com.agilefreaks.gripit.data.Serializer
import com.agilefreaks.gripit.view.BaseActivity
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity)

    fun context(): Context

    fun assetManager(): AssetManager

    fun serializer(): Serializer

    @Named("executionScheduler") fun executionScheduler(): Scheduler

    @Named("postExecutionScheduler") fun postExecutionScheduler(): Scheduler
}
