package com.agilefreaks.gripit.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.R
import javax.inject.Inject

class RouteDetailsActivity : AppCompatActivity() {
    @Inject lateinit var routeDetailsFragment: RouteDetailsContract.View

    companion object {
        val INTENT_EXTRA_PARAM_ROUTE_ID = "com.agilefreaks.INTENT_USER_ID"
        val INSTANCE_STATE_PARAM_ROUTE_ID = "com.agilefreaks.STATE_USER_ID"
    }

    var routeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)
        setupDagger()
        initializeActivity(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
        super.onSaveInstanceState(outState)
    }

    fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragment = routeDetailsFragment as RouteDetailsFragment
            val transaction = supportFragmentManager.beginTransaction()
            routeId = intent.getIntExtra(INTENT_EXTRA_PARAM_ROUTE_ID, -1)
            fragment.arguments = RouteDetailsFragment.forRoute(routeId)
            transaction.add(R.id.content_frame, fragment)
            transaction.commit()
        } else {
            routeId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_ROUTE_ID)
        }
    }

    private fun setupDagger() {
        DaggerRouteDetailsComponent.builder().
                applicationComponent((application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }
}
