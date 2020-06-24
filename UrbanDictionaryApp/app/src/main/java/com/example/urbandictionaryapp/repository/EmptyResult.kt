package com.example.urbandictionaryapp.repository

sealed class EmptyResult {
    class Error(val error: Exception) : EmptyResult()
    object Ok : EmptyResult()
}