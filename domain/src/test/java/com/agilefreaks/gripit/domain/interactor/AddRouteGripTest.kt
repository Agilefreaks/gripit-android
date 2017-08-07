package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.repository.RouteGripRepository
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class AddRouteGripTest {
    lateinit var addRouteGrip: AddRouteGrip

    @Mock lateinit var mockExecutionScheduler: Scheduler
    @Mock lateinit var mockPostExecutionScheduler: Scheduler
    @Mock lateinit var mockRouteRepository: RouteGripRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addRouteGrip = AddRouteGrip(mockRouteRepository, mockExecutionScheduler, mockPostExecutionScheduler)
    }

    @Test
    fun testGetRoutesUseCaseObservable() {
        val params = RouteGrip()
        addRouteGrip.buildUseCaseObservable(params)

        verify(mockRouteRepository).addRouteGrip(params)
        verifyNoMoreInteractions(mockRouteRepository)
        verifyZeroInteractions(mockExecutionScheduler)
        verifyZeroInteractions(mockPostExecutionScheduler)
    }
}