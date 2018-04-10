package com.agilefreaks.gripit.details.me

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.details.RouteDetailsFragment
import com.agilefreaks.gripit.domain.RouteMe
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.fragment_route_me.*

class RouteMeFragment : BaseView(), RouteMeContract.View {
    override lateinit var viewModel: RouteMeContract.ViewModel
    lateinit var routeMeAdapter: RouteMeAdapter

    companion object {
        fun build(viewModel: RouteMeContract.ViewModel, routeMeAdapter: RouteMeAdapter) = RouteMeFragment().also {
            it.viewModel = viewModel
            it.routeMeAdapter = routeMeAdapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_me, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRouteCollection()
    }

    override fun addRouteMe(routeMeList: Collection<RouteMe>) = routeMeAdapter.addRouteMe(routeMeList)

    override fun getRouteId(): Int = arguments!!.getInt(RouteDetailsFragment.PARAM_ROUTE_ID)

    private fun setupRouteCollection() {
        route_me_collection.layoutManager = LinearLayoutManager(this.context)
        route_me_collection.adapter = routeMeAdapter
        route_me_collection.itemAnimator = DefaultItemAnimator()
        route_me_collection.setHasFixedSize(true)
    }
}