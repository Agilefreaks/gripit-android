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
import com.agilefreaks.gripit.details.info.InfoPagerAdapter
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.fragment_route_details.*
import javax.inject.Inject


class RouteDetailsFragment @Inject constructor(override val viewModel: RouteDetailsContract.ViewModel) : BaseView(), RouteDetailsContract.View {
    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerRouteDetailsComponent.builder().
                applicationComponent((activity.application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_details, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollapsibleBar()

        tabs_route_details.addTab(tabs_route_details.newTab().setText("Info"))
        tabs_route_details.addTab(tabs_route_details.newTab().setText("Me"))
        tabs_route_details.addTab(tabs_route_details.newTab().setText("Others"))

        val adapter = InfoPagerAdapter(activity.fragmentManager)
        details_viewPager.adapter = adapter
        tabs_route_details.setupWithViewPager(details_viewPager)
    }

    fun setCollapsibleBar() {
        val activity = activity as RouteDetailsActivity
        activity.setSupportActionBar(route_toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        route_toolbar.setNavigationOnClickListener { navigator.navigateToRouteList() }
    }

    override fun getRouteId(): Int {
        val arguments = arguments
        return arguments.getInt(PARAM_ROUTE_ID)
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
