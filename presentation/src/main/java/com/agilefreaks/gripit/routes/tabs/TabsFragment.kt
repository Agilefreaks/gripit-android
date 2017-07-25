package com.agilefreaks.gripit.routes.tabs

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.databinding.DataBindingUtil
import android.support.design.widget.TabLayout
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.databinding.TabsFragmentBinding
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.routes.RoutesController
import com.agilefreaks.gripit.view.BaseView
import com.jakewharton.rxbinding2.support.design.widget.selectionEvents
import com.jakewharton.rxbinding2.support.design.widget.selections
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.tabs_fragment.*
import kotlinx.android.synthetic.main.tabs_fragment.view.*

class TabsFragment : BaseView(), TabsContract.View {
    lateinit override var viewModel: TabsContract.ViewModel

    companion object {
        fun build(viewModel: TabsContract.ViewModel) = TabsFragment().also { it.viewModel = viewModel }
    }

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<TabsFragmentBinding>(inflater, R.layout.tabs_fragment, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listenToTabSelection(tabs.selections())
    }
}