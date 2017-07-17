package com.agilefreaks.gripit.routes

import com.agilefreaks.gripit.domain.interactor.GetRoutes
import com.agilefreaks.gripit.routes.list.ListContract
import com.agilefreaks.gripit.routes.list.ListViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoutesViewModelTest {
    @InjectMocks lateinit var routesViewModel: ListViewModel

    @Mock lateinit var useCase: GetRoutes
    @Mock lateinit var viewCallBack: ListContract.View

    @Before
    fun setUp() {
        routesViewModel = ListViewModel(useCase)
    }

    @Test
    fun onViewAttachedWillFetchRoutesCollection() {
        // TODO: find a way to properly test that observable
    }
}