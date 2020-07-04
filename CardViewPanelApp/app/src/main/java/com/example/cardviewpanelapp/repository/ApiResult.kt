package com.example.cardviewpanelapp.repository

sealed class ApiResult<out R> {
    class Error(val error: Exception) : ApiResult<Nothing>()
    class Ok<out R>(val result: R) : ApiResult<R>()
}