package com.agilefreaks.gripit.grip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.R
import javax.inject.Inject

class RouteGripActivity : AppCompatActivity() {
    @Inject lateinit var routeGripFragment: RouteGripFragment

    var routeId: Int = 1
    var routeState: String = "Gripit"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_grip)
        setupDagger()
        initializeActivity(savedInstanceState)
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            routeGripFragment.arguments = RouteGripFragment.forRoute(routeId, routeState)
            transaction.add(R.id.route_grip_content_frame, routeGripFragment)
            transaction.commit()
        } else {
            routeId = savedInstanceState.getInt(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID)
            routeState = savedInstanceState.getString(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE)
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
        outState?.putString(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, routeState)
        super.onSaveInstanceState(outState)
    }

    companion object {
        val INTENT_EXTRA_PARAM_ROUTE_ID = "com.agilefreaks.INTENT_ROUTE_ID"
        val INTENT_EXTRA_PARAM_ROUTE_STATE = "com.agilefreaks.INTENT_ROUTE_STATE"

        val INSTANCE_STATE_PARAM_ROUTE_ID = "com.agilefreaks.STATE_ROUTE_ID"
        val INSTANCE_STATE_PARAM_ROUTE_STATE = "com.agilefreaks.STATE_ROUTE_STATE"
    }

    private fun setupDagger() {
        DaggerRouteGripComponent.builder().
                applicationComponent((application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }
}
