package com.agilefreaks.gripit.details.me

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteMeModel
import javax.inject.Inject

class RouteMeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val routeMeList: MutableList<RouteMeModel> = mutableListOf(RouteMeModel(date = "10/10/2010"), RouteMeModel(date = "10/10/2010"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.route_me_view, parent, false)
        return RouteMeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewDataBinding: ViewDataBinding = (holder as RouteMeViewHolder).viewDataBinding
        viewDataBinding.setVariable(BR.routeMe, routeMeList[position])
    }

    override fun getItemCount(): Int {
        return routeMeList.size
    }

    internal class RouteMeViewHolder(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        init {
            viewDataBinding.executePendingBindings()
        }
    }
}
