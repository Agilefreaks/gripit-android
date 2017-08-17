package com.agilefreaks.gripit.details.info

import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.details.me.RouteMeContract
import com.agilefreaks.gripit.details.me.RouteMeViewModel
import com.agilefreaks.gripit.domain.RouteMe
import com.agilefreaks.gripit.domain.interactor.GetRouteMe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test

class RouteMeViewModelTest {
    lateinit var routeMeViewModel: RouteMeContract.ViewModel

    val mockGetRouteMeUseCase = mock<GetRouteMe>()
    val mockNavigator = mock<Navigator>()

    @Before
    fun setup() {
        routeMeViewModel = RouteMeViewModel(mockGetRouteMeUseCase, mockNavigator)
    }

    @Test
    fun onViewAttachedWillCallExecute() {
        routeMeViewModel.onViewAttached(mock<RouteMeContract.View>())

        verify(mockGetRouteMeUseCase).executeWithConsumer(any<Consumer<Collection<RouteMe>>>(), any<Int>())
    }

    @Test
    fun onViewDetachedWillDispose() {
        routeMeViewModel.onViewDetached()

        verify(mockGetRouteMeUseCase).dispose()
    }
}