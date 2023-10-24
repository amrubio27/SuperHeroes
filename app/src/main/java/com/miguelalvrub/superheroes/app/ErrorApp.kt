package com.miguelalvrub.superheroes.app

sealed class ErrorApp {
    object UnknowError : ErrorApp()
}