package com.agilefreaks.gripit.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.routes.list.ListAdapter
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.view.BaseView

class RouteDetailsFragment constructor(override val viewModel: RouteDetailsContract.ViewModel) : BaseView(), RouteDetailsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.route_details_fragment, container, false)
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
