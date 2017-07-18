package com.agilefreaks.gripit.routes

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v4.app.Fragment
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.util.EspressoIdlingResource
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import com.agilefreaks.gripit.view.BaseActivity
import javax.inject.Inject

class RoutesActivity : BaseActivity() {
    @Inject lateinit var listFragment: ListContract.View
    @Inject lateinit var tabsFragment: TabsContract.View
    @Inject lateinit var routesController: RoutesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDagger()
        setContentView(R.layout.routes_activity)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, listFragment as Fragment)
        transaction.add(R.id.tabs_frame, tabsFragment as Fragment)
        transaction.commit()

        routesController.setup()
    }

    override fun onResume() {
        super.onResume()
        routesController.onResume()
    }

    private fun setupDagger() {
        DaggerRoutesComponent.builder().
                applicationComponent((application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource {
        return EspressoIdlingResource.idlingResource
    }
}