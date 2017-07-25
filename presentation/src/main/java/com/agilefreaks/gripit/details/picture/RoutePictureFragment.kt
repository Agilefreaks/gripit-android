package com.agilefreaks.gripit.details.picture

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.view.BaseView

class RoutePictureFragment : BaseView(), RoutePictureContract.View {
    override lateinit var viewModel: RoutePictureContract.ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_picture, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun getRoutePictureLocation(): String = arguments.getString(PARAM_ROUTE_PICTURE)

    companion object {
        val PARAM_ROUTE_PICTURE = "param_route_picture_location"

        fun forPicture(routePicture: String): Bundle {
            val args = Bundle()
            args.putString(PARAM_ROUTE_PICTURE, routePicture)
            return args
        }

        fun build(viewModel: RoutePictureContract.ViewModel) = RoutePictureFragment().also {
            it.viewModel = viewModel
        }
    }
}