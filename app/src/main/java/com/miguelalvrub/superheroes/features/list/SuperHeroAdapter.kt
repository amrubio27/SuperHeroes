package com.miguelalvrub.superheroes.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miguelalvrub.superheroes.R
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase

class SuperHeroAdapter() : RecyclerView.Adapter<SuperHeroViewHolder>() {

    private val dataList: MutableList<GetSuperHeroesFeedUseCase.Output> = mutableListOf()

    fun setData(superheroes: List<GetSuperHeroesFeedUseCase.Output>) {
        dataList.clear()
        addDataList(superheroes)
    }

    fun addDataList(superheroes: List<GetSuperHeroesFeedUseCase.Output>) {
        dataList.addAll(superheroes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_superhero_feed, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}