package com.malisoftware.components.uiEvent

sealed class UiEvent<T> (
    val data: T? = null,
    val message: String? = null,
){
    class Loading<T> : UiEvent<T>()
    class Success<T>(data: T) : UiEvent<T>(data)
    class Error<T>(message: String, data: T? = null) : UiEvent<T>(data, message)

}