package com.agilefreaks.gripit.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.R

class RouteDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.route_details_fragment, container, false)
    }

    companion object {
        private val PARAM_ROUTE_ID = "param_route_id"

        fun forRoute(routeId: Int): RouteDetailsFragment {
            val fragment = RouteDetailsFragment()
            val args = Bundle()
            args.putInt(PARAM_ROUTE_ID, routeId)
            fragment.arguments = args
            return fragment
        }
    }
}
