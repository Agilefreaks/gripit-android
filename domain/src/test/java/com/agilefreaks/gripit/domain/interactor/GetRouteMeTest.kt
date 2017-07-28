package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.repository.RouteMeRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GetRouteMeTest {
    lateinit var getRouteMe: GetRouteMe

    @Mock lateinit var mockExecutionScheduler: Scheduler
    @Mock lateinit var mockPostExecutionScheduler: Scheduler
    @Mock lateinit var mockRouteRepository: RouteMeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRouteMe = GetRouteMe(mockRouteRepository, mockExecutionScheduler, mockPostExecutionScheduler)
    }

    @Test
    fun testGetRoutesUseCaseObservable() {
        val params = 0
        getRouteMe.buildUseCaseObservable(params)

        verify(mockRouteRepository).routeTries(params)
        verifyNoMoreInteractions(mockRouteRepository)
        verifyZeroInteractions(mockExecutionScheduler)
        verifyZeroInteractions(mockPostExecutionScheduler)
    }
}