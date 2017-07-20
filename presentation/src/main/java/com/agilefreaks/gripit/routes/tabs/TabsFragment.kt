package com.agilefreaks.gripit.routes.tabs

import android.databinding.DataBindingUtil
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.databinding.FragmentTabsBinding
import com.agilefreaks.gripit.view.BaseView
import com.jakewharton.rxbinding2.support.design.widget.selections
import kotlinx.android.synthetic.main.fragment_tabs.*

class TabsFragment : BaseView(), TabsContract.View {
    lateinit override var viewModel: TabsContract.ViewModel

    companion object {
        fun build(viewModel: TabsContract.ViewModel) = TabsFragment().also { it.viewModel = viewModel }
    }

    override fun onCreateView(inflater: android.view.LayoutInflater, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentTabsBinding>(inflater, R.layout.fragment_tabs, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listenToTabSelection(tabs.selections())
    }
}