package com.agilefreaks.gripit.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilefreaks.gripit.R

class RouteDetailsActivity : AppCompatActivity() {
    companion object {
        val INTENT_EXTRA_PARAM_USER_ID = "com.agilefreaks.INTENT_USER_ID"
        val INSTANCE_STATE_PARAM_USER_ID = "com.agilefreaks.STATE_USER_ID"
    }

    var routeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)

        initializeActivity(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(INSTANCE_STATE_PARAM_USER_ID, routeId)
        super.onSaveInstanceState(outState)
    }

    fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            routeId = intent.getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1)
            val fragment = RouteDetailsFragment.forRoute(routeId)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.content_frame, fragment)
            transaction.commit()
        } else {
            routeId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID)
        }
    }
}
