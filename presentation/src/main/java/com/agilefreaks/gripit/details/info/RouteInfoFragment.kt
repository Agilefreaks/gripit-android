package com.agilefreaks.gripit.details.info

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.details.RouteDetailsFragment
import com.agilefreaks.gripit.view.BaseView
import javax.inject.Inject

class RouteInfoFragment @Inject constructor(override val viewModel: RouteInfoContract.ViewModel) : BaseView(), RouteInfoContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getRouteId(): Int {
        return arguments.getInt(RouteDetailsFragment.PARAM_ROUTE_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_info, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }
}