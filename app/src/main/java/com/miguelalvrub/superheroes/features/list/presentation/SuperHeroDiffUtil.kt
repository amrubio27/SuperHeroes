package com.miguelalvrub.superheroes.features.list.presentation

import androidx.recyclerview.widget.DiffUtil
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase

class SuperHeroDiffUtil : DiffUtil.ItemCallback<GetSuperHeroesFeedUseCase.Output>() {

    override fun areItemsTheSame(
        oldItem: GetSuperHeroesFeedUseCase.Output,
        newItem: GetSuperHeroesFeedUseCase.Output
    ): Boolean {
        return oldItem.superHero.id == newItem.superHero.id
    }

    override fun areContentsTheSame(
        oldItem: GetSuperHeroesFeedUseCase.Output,
        newItem: GetSuperHeroesFeedUseCase.Output
    ): Boolean {
        return oldItem == newItem
    }
}