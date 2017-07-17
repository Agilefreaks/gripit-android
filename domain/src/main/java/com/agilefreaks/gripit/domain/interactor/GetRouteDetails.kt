package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.repository.RouteRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class GetRouteDetails
@Inject constructor(val repository: RouteRepository,
                    @Named("executionScheduler") executionScheduler: Scheduler,
                    @Named("postExecutionScheduler") postExecutionScheduler: Scheduler) :
        UseCase<Route, Any>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCaseObservable(params: Any?): Observable<Route> {
        return repository.route(params as Int)
    }
}
