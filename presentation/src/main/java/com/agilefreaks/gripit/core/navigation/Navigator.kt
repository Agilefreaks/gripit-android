package com.agilefreaks.gripit.core.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.details.RouteDetailsActivity
import com.agilefreaks.gripit.grip.RouteGripActivity
import com.agilefreaks.gripit.routes.RoutesActivity
import javax.inject.Inject


class Navigator @Inject constructor(val applicationContext: Context) {

    fun navigateToRouteList() {
        val intentToLaunch = Intent(applicationContext, RoutesActivity::class.java)
        intentToLaunch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        applicationContext.startActivity(intentToLaunch)
    }

    fun navigateToRouteDetails(routeId: Int) {
        val intentToLaunch = Intent(applicationContext, RouteDetailsActivity::class.java)
        intentToLaunch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intentToLaunch.putExtra(RouteDetailsActivity.INTENT_EXTRA_PARAM_ROUTE_ID, routeId)
        applicationContext.startActivity(intentToLaunch)
    }

    fun navigateToGripScreen(routeId: Int, routeState: RouteState) {
        val intentToLaunch = Intent(applicationContext, RouteGripActivity::class.java)
        intentToLaunch.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
        intentToLaunch.putExtra(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, routeState)
        applicationContext.startActivity(intentToLaunch)
    }

    fun navitateToVideoPlay(videoPath: String) {
        val uri = Uri.parse(videoPath)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setDataAndType(uri, "video/*")
        applicationContext.startActivity(intent)
    }
}
