package com.agilefreaks.gripit.view

import android.support.v4.app.Fragment
import com.agilefreaks.gripit.core.Lifecycle

abstract class BaseView : Fragment(), Lifecycle.View {
    abstract val viewModel: Lifecycle.ViewModel

    override fun onStart() {
        super.onStart()
        viewModel.onViewAttached(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onViewPaused()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewDetached()
    }
}