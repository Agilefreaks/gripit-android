package com.agilefreaks.gripit.routes.list

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.fragment_route.*

class ListFragment constructor(override val viewModel: ListContract.ViewModel, val listAdapter: ListAdapter) : BaseView(), ListContract.View {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_route, container, false)
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRouteCollection()
    }

    override fun showRoutes(routes: Collection<Route>) = listAdapter.appendRoutes(routes)

    private fun setupRouteCollection() {
        route_collection.layoutManager = LinearLayoutManager(this.context)
        route_collection.adapter = listAdapter
        route_collection.itemAnimator = DefaultItemAnimator()
        route_collection.setHasFixedSize(true)
    }
}