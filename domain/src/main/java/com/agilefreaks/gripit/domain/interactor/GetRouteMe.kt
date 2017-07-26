package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.RouteMe
import com.agilefreaks.gripit.domain.repository.RouteMeRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class GetRouteMe
@Inject constructor(val repository: RouteMeRepository,
                    @Named("executionScheduler") executionScheduler: Scheduler,
                    @Named("postExecutionScheduler") postExecutionScheduler: Scheduler) :
        UseCase<Collection<RouteMe>, Any>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCaseObservable(params: Any?): Observable<Collection<RouteMe>> {
        return repository.routeTries(params as Int)
    }
}
