package com.agilefreaks.gripit.details

import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.interactor.GetRouteDetails
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test

class RouteDetailsViewModelTest {
    lateinit var routeDetailsViewModel: RouteDetailsContract.ViewModel

    val mockGetRouteDetailsUseCase = mock<GetRouteDetails>()

    @Before
    fun setup() {
        routeDetailsViewModel = RouteDetailsViewModel(mockGetRouteDetailsUseCase)
    }

    @Test
    fun onViewAttachedWillCallExecute() {
        routeDetailsViewModel.onViewAttached(mock<RouteDetailsContract.View>())

        verify(mockGetRouteDetailsUseCase).execute(any<DisposableObserver<Route>>(), any<Int>())
    }

    @Test
    fun onViewDetachedWillDispose() {
        routeDetailsViewModel.onViewDetached()

        verify(mockGetRouteDetailsUseCase).dispose()
    }
}