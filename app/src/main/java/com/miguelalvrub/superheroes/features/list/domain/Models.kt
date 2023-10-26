package com.miguelalvrub.superheroes.features.list.domain

class Models {
    data class SuperHero(
        val id: Int,
        val name: String,
        val images: String //esto era una lista de imagenes
    )

    data class Biography(
        val fullName: String,
    )

    data class Work(
        val occupation: String,
    )

}