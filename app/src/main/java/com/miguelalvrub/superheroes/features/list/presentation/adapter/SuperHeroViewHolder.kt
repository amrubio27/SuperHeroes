package com.miguelalvrub.superheroes.features.list.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.miguelalvrub.superheroes.app.extensions.setUrl
import com.miguelalvrub.superheroes.databinding.ViewItemSuperheroFeedBinding
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase

class SuperHeroViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ViewItemSuperheroFeedBinding

    fun bind(superhero: GetSuperHeroesFeedUseCase.Output) {
        binding = ViewItemSuperheroFeedBinding.bind(view)

        binding.apply {
            imagePreview.setUrl(superhero.superHero.images)
            superHeroName.text = superhero.superHero.name
            superHeroRealName.text = superhero.biography.fullName
            superHeroOccupation.text = superhero.work.occupation
        }
    }
}

