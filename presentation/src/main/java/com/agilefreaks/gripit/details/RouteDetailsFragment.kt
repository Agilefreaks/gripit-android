package com.agilefreaks.gripit.details

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.details.info.RouteInfoFragment
import com.agilefreaks.gripit.details.me.RouteMeFragment
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.fragment_route_details.*
import javax.inject.Inject

class RouteDetailsFragment : BaseView(), RouteDetailsContract.View {
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var routeInfoFragment: RouteInfoFragment
    @Inject lateinit var routeMeFragment: RouteMeFragment
    override lateinit var viewModel: RouteDetailsContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDagger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_details, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollapsibleBar()
        setTabLayout()
    }

    fun setCollapsibleBar() {
        val activity = activity as RouteDetailsActivity
        activity.setSupportActionBar(route_toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        route_toolbar.setNavigationOnClickListener { navigator.navigateToRouteList() }
    }

    fun setTabLayout() {
        if (routeInfoFragment.arguments == null) {
            routeInfoFragment.arguments = arguments
            routeMeFragment.arguments = arguments
        }

        details_viewPager.adapter = RouteDetailsPagerAdapter(childFragmentManager).also {
            it.addFragment(routeInfoFragment, "Info")
            it.addFragment(routeMeFragment, "Route Me")
        }

        tabs_route_details.setupWithViewPager(details_viewPager)
    }

    override fun getRouteId(): Int {
        return arguments!!.getInt(PARAM_ROUTE_ID)
    }

    private fun setupDagger() {
        DaggerRouteDetailsComponent.builder().
                applicationComponent((activity!!.application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }

    companion object {
        val PARAM_ROUTE_ID = "param_route_id"

        fun forRoute(routeId: Int): Bundle {
            val args = Bundle()
            args.putInt(RouteDetailsFragment.PARAM_ROUTE_ID, routeId)
            return args
        }

        fun build(viewModel: RouteDetailsContract.ViewModel) = RouteDetailsFragment().also {
            it.viewModel = viewModel
        }
    }
}
