package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.tabs.TabsContract
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.processors.BehaviorProcessor
import org.junit.Test

class RoutesControllerTest {
    private val listViewModel = mock<ListContract.ViewModel>()
    private val tabsViewModel = mock<TabsContract.ViewModel>()
    private val navigator = mock<Navigator>()

    val controller = RoutesController(listViewModel, tabsViewModel, navigator)

    @Test
    fun onResumeWillSubscribeToTabSelections() {
        val tabsSelected = BehaviorProcessor.create<String>()
        given(tabsViewModel.tabSelected).willReturn(tabsSelected)

        controller.onResume()
        tabsSelected.onNext("test")

        verify(listViewModel).filter("test")
    }

    @Test
    fun willNavigate() {
        val route = mock<Route>()

        controller.navigate(route)

        verify(navigator).navigateToRouteDetails(any<Int>())
    }

    @Test
    fun onDestroyWillDisposeViewModels() {
        controller.onDestroy()

        verify(listViewModel).dispose()
        verify(tabsViewModel).dispose()
    }

}