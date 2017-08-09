package com.agilefreaks.gripit.details.grip

import android.content.Context
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.domain.interactor.AddRouteGrip
import com.agilefreaks.gripit.grip.RouteGripContract
import com.agilefreaks.gripit.grip.RouteGripViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RouteGripViewModelTest {
    lateinit var routeGripViewModel: RouteGripViewModel

    val mockAddRouteGripUseCase = mock<AddRouteGrip>()
    val navigator = mock<Navigator>()

    @Before
    fun setup() {
        routeGripViewModel = RouteGripViewModel(mock<Context>(), mockAddRouteGripUseCase, navigator)
    }

    @Test
    fun onViewAttachedTest() {
        Mockito.`when`(routeGripViewModel.viewCallback.getRouteState()).thenReturn(RouteState.GripIt)

        routeGripViewModel.onViewAttached(mock<RouteGripContract.View>())

        verify(routeGripViewModel.buttonState).set(any())
        verify(routeGripViewModel.screenTitle).set(any())
    }

}