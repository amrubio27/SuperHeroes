package com.miguelalvrub.superheroes.features.list.data.superhero.remote.api

import com.google.gson.annotations.SerializedName

data class SuperHeroApiModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("images") var images: Images
)

data class Images(
    @SerializedName("sm") var sm: String

)