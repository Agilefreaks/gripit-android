package com.agilefreaks.gripit.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.routes.DaggerRoutesComponent
import kotlinx.android.synthetic.main.activity_route_details.*
import javax.inject.Inject

class RouteDetailsActivity : AppCompatActivity() {
    @Inject lateinit var navigator: Navigator

    companion object {
        val INTENT_EXTRA_PARAM_USER_ID = "com.agilefreaks.INTENT_USER_ID"
        val INSTANCE_STATE_PARAM_USER_ID = "com.agilefreaks.STATE_USER_ID"
    }

    var routeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)
        setupDagger()
        setHomeNavigation()
        initializeActivity(savedInstanceState)
    }

    fun setHomeNavigation() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { navigator.navigateToRouteList() }
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

    private fun setupDagger() {
        DaggerRouteDetailsComponent.builder().
                applicationComponent((application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }
}
