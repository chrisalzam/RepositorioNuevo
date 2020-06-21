package com.r2devpros.myrestapptest.repository

sealed class EmptyResult {
    class Error(val error: Exception) : EmptyResult()
    object Ok : EmptyResult()
}