package com.agilefreaks.gripit.details.picture

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_route_picture.*

class RoutePictureFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_route_picture, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            loadRoutePicture()
        }
    }

    private fun loadRoutePicture() {
        val arguments = arguments
        Picasso.with(context).load("file:///android_asset/" + arguments.getString(PARAM_ROUTE_PICTURE)).
                into(route_detail_picture)
    }

    companion object {
        val PARAM_ROUTE_PICTURE = "param_route_picture_location"

        fun forPicture(routePicture: String): Bundle {
            val args = Bundle()
            args.putString(PARAM_ROUTE_PICTURE, routePicture)
            return args
        }
    }
}