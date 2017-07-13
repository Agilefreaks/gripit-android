package com.agilefreaks.gripit.details

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.route_details_fragment.*
import javax.inject.Inject

class RouteDetailsFragment @Inject constructor(override val viewModel: RouteDetailsContract.ViewModel, var navigator: Navigator) : BaseView(), RouteDetailsContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.route_details_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollapsibleBar()
        if (savedInstanceState == null) {
            loadRouteDetails()
        }
    }

    fun setCollapsibleBar() {
        val activity = activity as RouteDetailsActivity
        activity.setSupportActionBar(route_toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        route_toolbar.setNavigationOnClickListener { navigator.navigateToRouteList() }

        val bitmap = BitmapFactory.decodeResource(resources,
                R.drawable.placeholder_header)

        Palette.from(bitmap).generate { palette ->
            val vibrantColor = palette.getVibrantColor(R.color.colorPrimary)
            collapsing_toolbar.setContentScrimColor(vibrantColor)
            collapsing_toolbar.setStatusBarScrimColor(R.color.black_trans80)
        }
    }

    private fun loadRouteDetails() {
        val arguments = arguments
        viewModel.setRouteId(arguments.getInt(PARAM_ROUTE_ID))
    }

    companion object {
        val PARAM_ROUTE_ID = "param_route_id"

        fun forRoute(routeId: Int): Bundle {
            val args = Bundle()
            args.putInt(RouteDetailsFragment.PARAM_ROUTE_ID, routeId)
            return args
        }
    }
}
