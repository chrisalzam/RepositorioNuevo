package com.example.cardviewpanelapp.repository

sealed class EmptyResult {
    class Error(val error: Exception) : EmptyResult()
    object Ok : EmptyResult()
}