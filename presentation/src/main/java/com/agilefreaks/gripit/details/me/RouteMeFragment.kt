package com.agilefreaks.gripit.details.me

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.view.BaseView

class RouteMeFragment : BaseView(), RouteMeContract.View {
    override lateinit var viewModel: RouteMeContract.ViewModel

    companion object {
        fun build(viewModel: RouteMeContract.ViewModel) = RouteMeFragment().also {
            it.viewModel = viewModel
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_me, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }
}