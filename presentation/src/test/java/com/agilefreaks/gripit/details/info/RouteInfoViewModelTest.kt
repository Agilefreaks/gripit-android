package com.agilefreaks.gripit.details.info

import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test

class RouteInfoViewModelTest {
    lateinit var routeInfoViewModel: RouteInfoContract.ViewModel

    val mockGetRouteDetailsUseCase = mock<GetRouteDetails>()

    @Before
    fun setup() {
        routeInfoViewModel = RouteInfoViewModel(mockGetRouteDetailsUseCase)
    }

    @Test
    fun onViewAttachedWillCallExecute() {
        routeInfoViewModel.onViewAttached(mock<RouteInfoContract.View>())

        verify(mockGetRouteDetailsUseCase).execute(any<DisposableObserver<Route>>(), any<Int>())
    }

    @Test
    fun onViewDetachedWillDispose() {
        routeInfoViewModel.onViewDetached()

        verify(mockGetRouteDetailsUseCase).dispose()
    }
}