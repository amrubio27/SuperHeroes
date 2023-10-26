package com.miguelalvrub.superheroes.app

sealed class ErrorApp {
    object UnknowError : ErrorApp()
    object DataError : ErrorApp()
    object NetworkError : ErrorApp()
}