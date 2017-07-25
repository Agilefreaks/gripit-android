package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.processors.BehaviorProcessor
import org.junit.Test

class RoutesControllerTest {
    private val listViewModel = mock<ListContract.ViewModel>()
    private val tabsViewModel = mock<TabsContract.ViewModel>()

    val controller = RoutesController(listViewModel, tabsViewModel)

    @Test
    fun onResumeWillSubscribeToTabSelections() {
        val tabsSelected = BehaviorProcessor.create<String>()
        given(tabsViewModel.tabSelected).willReturn(tabsSelected)

        controller.onResume()
        tabsSelected.onNext("test")

        verify(listViewModel).filter("test")
    }
}