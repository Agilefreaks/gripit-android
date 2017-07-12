package com.agilefreaks.gripit.core.navigation

import android.content.Context
import android.content.Intent
import com.agilefreaks.gripit.details.RouteDetailsActivity
import com.agilefreaks.gripit.routes.RoutesActivity
import javax.inject.Inject
import javax.inject.Singleton


class Navigator @Inject constructor(val applicationContext: Context) {

    fun navigateToRouteList() {
        val intentToLaunch = Intent(applicationContext, RoutesActivity::class.java)
        applicationContext.startActivity(intentToLaunch)

    }

    fun navigateToRouteDetails(userId: Int) {
        val intentToLaunch = Intent(applicationContext, RouteDetailsActivity::class.java)
        intentToLaunch.putExtra(RouteDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, userId)
        applicationContext.startActivity(intentToLaunch)
    }
}