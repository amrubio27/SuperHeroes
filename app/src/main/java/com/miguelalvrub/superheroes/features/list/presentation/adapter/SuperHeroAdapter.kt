package com.miguelalvrub.superheroes.features.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.miguelalvrub.superheroes.R
import com.miguelalvrub.superheroes.features.list.presentation.SuperHeroDiffUtil
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase

class SuperHeroAdapter() : ListAdapter<GetSuperHeroesFeedUseCase.Output, SuperHeroViewHolder>(
    SuperHeroDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_superhero_feed, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}