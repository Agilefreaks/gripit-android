package com.agilefreaks.gripit.domain.interactor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

abstract class UseCase<T, in Params> internal constructor(
        private val executionScheduler: Scheduler,
        private val postExecutionScheduler: Scheduler) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: Any?): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
        addDisposable(observable(params).subscribeWith(observer))
    }

    fun executeWithConsumer(onNext: Consumer<T>, params: Params) {
        addDisposable(observable(params).subscribe(onNext))
    }

    fun executeSingle(params: Params) {
        addDisposable(observable(params).subscribe())
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun observable(params: Params): Observable<T> {
        return this.buildUseCaseObservable(params)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler)

    }
}
