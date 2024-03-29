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
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.view.BaseView
import kotlinx.android.synthetic.main.fragment_route_grip.*
import javax.inject.Inject

class RouteGripFragment : BaseView(), RouteGripContract.View {
    override lateinit var viewModel: RouteGripContract.ViewModel
    @Inject lateinit var navigator: Navigator

    companion object {
        val REQUEST_VIDEO_FROM_GALLERY = 1000

        val PARAM_ROUTE_ID = "param_route_id"
        val PARAM_ROUTE_STATE = "param_state"

        fun build(viewModel: RouteGripContract.ViewModel) = RouteGripFragment().also {
            it.viewModel = viewModel
        }

        fun forRoute(routeId: Int, routeState: RouteState): Bundle {
            val args = Bundle()
            args.putInt(PARAM_ROUTE_ID, routeId)
            args.putSerializable(PARAM_ROUTE_STATE, routeState)
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
    }

    override fun loadFromGallery() {
        val pickIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            it.type = "video/*"
        }
        startActivityForResult(pickIntent, REQUEST_VIDEO_FROM_GALLERY)
    }


    fun setToolbar() {
        val activity = activity as RouteGripActivity
        activity.setSupportActionBar(grip_toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        grip_toolbar.setNavigationOnClickListener { navigator.navigateToRouteDetails(getRouteId()) }
    }

    override fun getRouteState(): RouteState = arguments.getSerializable(PARAM_ROUTE_STATE) as RouteState

    override fun getRouteId(): Int = arguments.getInt(PARAM_ROUTE_ID)

    private fun setupDagger() {
        DaggerRouteGripComponent.builder().
                applicationComponent((activity.application as AndroidApplication).applicationComponent).
                build().
                inject(this)
    }
}
