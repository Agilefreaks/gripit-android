package com.agilefreaks.gripit.routes.tabs

import android.support.design.widget.TabLayout
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.domain.None
import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.domain.interactor.GetFilterUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.functions.Consumer
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

class TabsViewModelTest {
    lateinit var tabsViewModel: TabsContract.ViewModel

    val mockGetFilterUseCase = mock<GetFilterUseCase>()

    @Before
    fun setup() {
        tabsViewModel = TabsViewModel(mockGetFilterUseCase)
    }

    @Test
    fun onViewAttachedWillCallExecute() {
        tabsViewModel.onViewAttached(mock<Lifecycle.View>())

        verify(mockGetFilterUseCase).executeWithConsumer(any<Consumer<Collection<RouteFilter>>>(), any<None>())
    }

    @Test
    fun onViewDetacheddWillDispose() {
        tabsViewModel.onViewDetached()

        verify(mockGetFilterUseCase).dispose()
    }

    @Test
    fun onTabSelectionChanged()  {
        val subject = BehaviorProcessor.create<TabLayout.Tab>()
        val subscriber = TestSubscriber<String>()

        tabsViewModel.listenToTabSelection(subject.toObservable())
        tabsViewModel.tabSelected.subscribe(subscriber)
        subject.onNext(mock<TabLayout.Tab> { on { tag }.doReturn("Test") })

        subscriber.assertValue("Test")
    }
}