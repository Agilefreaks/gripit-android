package com.agilefreaks.gripit.details.grip


import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.core.navigation.Navigator
import com.agilefreaks.gripit.domain.interactor.AddRouteGrip
import com.agilefreaks.gripit.grip.RouteGripContract
import com.agilefreaks.gripit.grip.RouteGripViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RouteGripViewModelTest {
    lateinit var routeGripViewModel: RouteGripViewModel

    val mockAddRouteGripUseCase = mock<AddRouteGrip>()
    val mockViewCallback = mock<RouteGripContract.View>()
    val navigator = mock<Navigator>()

    @Before
    fun setup() {
        routeGripViewModel = RouteGripViewModel(mock(), mockAddRouteGripUseCase, navigator)
    }

    @Test
    fun onViewAttachedTest() {
        Mockito.`when`(mockViewCallback.getRouteState()).thenReturn(RouteState.GripIt)

        routeGripViewModel.onViewAttached(mockViewCallback)

        assertThat(routeGripViewModel.routeState, `is`(RouteState.GripIt))
        assertThat(routeGripViewModel.buttonState.get(), `is`(RouteState.GripIt.toString()))
        assertThat(routeGripViewModel.screenTitle.get(), `is`(RouteState.GripIt.toString() + " Screen"))
    }

    @Test
    fun onButtonClickTest() {
        routeGripViewModel.viewCallback = mockViewCallback
        routeGripViewModel.onButtonClick(mock())

        verify(mockAddRouteGripUseCase).execute(any(), any())
    }
}