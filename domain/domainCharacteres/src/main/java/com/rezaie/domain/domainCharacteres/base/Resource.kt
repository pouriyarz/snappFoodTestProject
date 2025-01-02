package com.rezaie.domain.domainCharacteres.base

sealed class Resource<out R> {

    object None : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exception) : Resource<Nothing>()
    data class Loading(val loading: Boolean) : Resource<Nothing>()
    object Canceled : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Exception=$error]"
            is Loading -> "Loading"
            Canceled -> "Canceled"
            None -> "None"
        }
    }

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isNone() = this is None
    fun isError() = this is Error
    fun isCanceled() = this is Canceled
}

inline fun <T> Resource<T>.onError(onFailure: (Exception) -> Unit) {
    if (this is Resource.Error) {
        onFailure(error)
    }
}

inline fun <R> Resource<R>.onLoading(action: (Boolean) -> Unit): Resource<R> {
    if (this is Resource.Loading) {
        action(this.loading)
    } else {
        action(false)
    }
    return this
}

inline fun <R> Resource<R>.onSuccess(action: (R) -> Unit): Resource<R> {
    if (this is Resource.Success) {
        action(data)
    }
    return this
}

fun <R> Resource<R>.getData(): R? {
    return (this as? Resource.Success)?.data
}