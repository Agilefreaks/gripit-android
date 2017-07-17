package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.RouteFilter
import com.agilefreaks.gripit.domain.repository.RouteFilterRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class GetFilter
@Inject constructor(val repository: RouteFilterRepository,
                    @Named("executionScheduler") executionScheduler: Scheduler,
                    @Named("postExecutionScheduler") postExecutionScheduler: Scheduler) :
        UseCase<Collection<RouteFilter>, Any>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCaseObservable(params: Any?): Observable<Collection<RouteFilter>> {
        return repository.filters()
    }
}
