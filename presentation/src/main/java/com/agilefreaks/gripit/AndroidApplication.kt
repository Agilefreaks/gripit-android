package com.agilefreaks.gripit

import android.app.Application
import com.agilefreaks.gripit.core.modules.ApplicationModule

class AndroidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set
        get

    override fun onCreate() {
        super.onCreate()
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}