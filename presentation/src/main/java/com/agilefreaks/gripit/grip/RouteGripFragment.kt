package com.agilefreaks.gripit.grip

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agilefreaks.gripit.AndroidApplication
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.view.BaseView

class RouteGripFragment : BaseView(), RouteGripContract.View {
    override lateinit var viewModel: RouteGripContract.ViewModel

    companion object {
        val REQUEST_VIDEO_FROM_GALLERY = 1000

        val PARAM_ROUTE_ID = "param_route_id"
        val PARAM_ROUTE_STATE = "param_state"

        fun build(viewModel: RouteGripContract.ViewModel) = RouteGripFragment().also {
            it.viewModel = viewModel
        }

        fun forRoute(routeId: Int, routeState: String): Bundle {
            val args = Bundle()
            args.putInt(PARAM_ROUTE_ID, routeId)
            args.putString(PARAM_ROUTE_STATE, routeState)
            return args
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDagger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_route_grip, container, false)

        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_VIDEO_FROM_GALLERY) {
                viewModel.handleGalleryResult(data!!.data)
            }
        }
    }

    override fun loadFromGallery() {
        val pickIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            it.type = "video/*"
        }
        startActivityForResult(pickIntent, REQUEST_VIDEO_FROM_GALLERY)
    }

    override fun getRouteState(): String = arguments.getString(PARAM_ROUTE_STATE)

    private fun setupDagger() {
        DaggerRouteGripComponent.builder().
                applicationComponent((activity.application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }
}
