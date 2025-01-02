package com.rezaie.domain.domainCharacteres.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

abstract class FlowUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val showLoading: Boolean = true
) {
    operator fun invoke(parameters: P): Flow<Resource<R>> = try {
        execute(parameters).onStart {
            emit(Resource.Loading(showLoading))
        }.catch { throwable ->
            emit(Resource.Error(Exception(throwable)))
        }.flowOn(coroutineDispatcher)
    } catch (t: Exception) {
        flow { emit(Resource.Error(t)) }
    }

    protected abstract fun execute(parameters: P): Flow<Resource<R>>
}
