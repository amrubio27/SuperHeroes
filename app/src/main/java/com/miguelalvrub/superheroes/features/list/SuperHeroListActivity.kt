package com.miguelalvrub.superheroes.features.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelalvrub.superheroes.databinding.ActivityMainBinding
import com.miguelalvrub.superheroes.features.list.data.biography.BiographyDataRepository
import com.miguelalvrub.superheroes.features.list.data.biography.remote.BiographyRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.superhero.SuperHeroDataRepository
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.SuperHeroRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.work.WorkDataRepository
import com.miguelalvrub.superheroes.features.list.data.work.remote.WorkRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val superheroAdapter = SuperHeroAdapter()

    private val superHeroRepository = GetSuperHeroesFeedUseCase(
        SuperHeroDataRepository(
            SuperHeroRemoteDataSource()
        ),
        BiographyDataRepository(
            BiographyRemoteDataSource()
        ),
        WorkDataRepository(
            WorkRemoteDataSource()
        )
    )


    val viewModel: SuperHeroListViewModel by lazy {
        SuperHeroListViewModel(
            superHeroRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObservers()

        viewModel.loadSuperHeroesFeed()

    }


    private fun setupObservers() {
        val observer = Observer<SuperHeroListViewModel.UiState> {
            //val list = it.superHero
            it.superHero?.apply {
                superheroAdapter.setData(this)
            }
            //Log.d("@dev", "lista: $list")
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
        binding.apply {
            superherolist.layoutManager = LinearLayoutManager(
                this@SuperHeroListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            superherolist.adapter = superheroAdapter
        }
    }

}