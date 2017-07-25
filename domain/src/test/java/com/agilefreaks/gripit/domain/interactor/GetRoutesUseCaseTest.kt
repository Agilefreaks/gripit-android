package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.repository.RouteRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetRoutesUseCaseTest {
    lateinit var getRoutes: GetRoutesUseCase

    @Mock lateinit var mockExecutionScheduler: Scheduler
    @Mock lateinit var mockPostExecutionScheduler: Scheduler
    @Mock lateinit var mockRouteRepository: RouteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRoutes = GetRoutesUseCase(mockRouteRepository, mockExecutionScheduler, mockPostExecutionScheduler)
    }

    @Test
    fun testGetRoutesUseCaseObservable() {
        val params = ""
        getRoutes.buildUseCaseObservable(params)

        verify(mockRouteRepository).routes(params)
        verifyNoMoreInteractions(mockRouteRepository)
        verifyZeroInteractions(mockExecutionScheduler)
        verifyZeroInteractions(mockPostExecutionScheduler)
    }
}