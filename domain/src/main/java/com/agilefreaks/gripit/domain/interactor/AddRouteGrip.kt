package com.agilefreaks.gripit.domain.interactor

import com.agilefreaks.gripit.domain.Route
import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.repository.RouteGripRepository
import com.agilefreaks.gripit.domain.repository.RouteRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class AddRouteGrip
@Inject constructor(val repository: RouteGripRepository,
                    @Named("executionScheduler") executionScheduler: Scheduler,
                    @Named("postExecutionScheduler") postExecutionScheduler: Scheduler) :
        UseCase<Unit, Any>(executionScheduler, postExecutionScheduler) {

    override fun buildUseCaseObservable(params: Any?): Observable<Unit> {
        return repository.addRouteGrip(params as RouteGrip)
    }
}
