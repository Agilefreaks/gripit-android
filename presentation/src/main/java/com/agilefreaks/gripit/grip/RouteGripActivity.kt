package com.agilefreaks.gripit.grip

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteState
import javax.inject.Inject

class RouteGripActivity : AppCompatActivity() {
    @Inject lateinit var routeGripFragment: RouteGripFragment

    var routeId: Int = 1
    var routeState: RouteState = RouteState.GripIt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_grip)
        setupDagger()
        requestPermission()
        initializeActivity(savedInstanceState)
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_STORAGE)
            }
        }
    }

    private fun initializeActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            routeId = intent.getIntExtra(INSTANCE_STATE_PARAM_ROUTE_ID, -1)
            routeState = intent.getSerializableExtra(INSTANCE_STATE_PARAM_ROUTE_STATE) as RouteState
            routeGripFragment.arguments = RouteGripFragment.forRoute(routeId, routeState)
            transaction.add(R.id.route_grip_content_frame, routeGripFragment)
            transaction.commit()
        } else {
            routeId = savedInstanceState.getInt(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID)
            routeState = savedInstanceState.getSerializable(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE) as RouteState
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_ID, routeId)
        outState?.putSerializable(RouteGripActivity.INSTANCE_STATE_PARAM_ROUTE_STATE, routeState)
        super.onSaveInstanceState(outState)
    }

    companion object {
        val READ_STORAGE = 1001

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
