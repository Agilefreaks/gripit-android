package com.agilefreaks.gripit.core.navigation

import android.content.Context
import android.content.Intent
import com.agilefreaks.gripit.details.RouteDetailsActivity
import com.agilefreaks.gripit.routes.RoutesActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mihai on 12.07.2017.
 */
@Singleton
class Navigator @Inject
constructor()//empty
{

    /**
     * Goes to the user list screen.

     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToRouteList(context: Context?) {
        if (context != null) {
            val intentToLaunch = Intent(context, RoutesActivity::class.java)
            context.startActivity(intentToLaunch)
        }
    }

    /**
     * Goes to the user details screen.

     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToRouteDetails(context: Context?, userId: Int) {
        if (context != null) {
            val intentToLaunch = Intent(context, RouteDetailsActivity::class.java)
            intentToLaunch.putExtra(RouteDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, userId)
            context.startActivity(intentToLaunch)
        }
    }
}
