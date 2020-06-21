package com.r2devpros.myrestapptest.repository

sealed class ApiResult<out R> {
    class Error(val error: Exception) : ApiResult<Nothing>()
    class Ok<out R>(val result: R) : ApiResult<R>()
}