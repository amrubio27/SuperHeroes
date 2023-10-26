package com.miguelalvrub.superheroes.features.list.data.work.remote.api

import com.miguelalvrub.superheroes.features.list.domain.Models

fun WorkApiModel.toModel(): Models.Work =
    Models.Work(this.occupation)