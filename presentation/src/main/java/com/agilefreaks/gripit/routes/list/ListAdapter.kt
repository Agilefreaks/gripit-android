package com.agilefreaks.gripit.routes.list

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.routes.RoutesController
import com.agilefreaks.gripit.core.model.RouteModel
import javax.inject.Inject


class ListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val routes: MutableList<Route> = mutableListOf()
    @Inject lateinit var controller: RoutesController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.route_view, parent, false)
        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewDataBinding: ViewDataBinding = (holder as RouteViewHolder).viewDataBinding
        viewDataBinding.setVariable(BR.route, RouteModel(routes[position]))
        viewDataBinding.root.setOnClickListener({
            controller.navigate(routes[position])
        })
    }

    override fun getItemCount(): Int {
        return routes.size
    }

    fun appendRoutes(newRoutes: Collection<Route>) {
        routes.clear()
        routes.addAll(newRoutes)
        notifyDataSetChanged()
    }

    internal class RouteViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        init {
            viewDataBinding.executePendingBindings()
        }
    }
}

