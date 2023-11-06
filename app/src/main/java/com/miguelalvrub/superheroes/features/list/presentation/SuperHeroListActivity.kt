package com.miguelalvrub.superheroes.features.list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelalvrub.superheroes.databinding.ActivityMainBinding
import com.miguelalvrub.superheroes.features.list.data.ApiClient
import com.miguelalvrub.superheroes.features.list.data.biography.BiographyDataRepository
import com.miguelalvrub.superheroes.features.list.data.biography.remote.BiographyRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.superhero.SuperHeroDataRepository
import com.miguelalvrub.superheroes.features.list.data.superhero.local.XmlSuperHeroLocalDataSource
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.SuperHeroRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.work.WorkDataRepository
import com.miguelalvrub.superheroes.features.list.data.work.remote.WorkRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.miguelalvrub.superheroes.features.list.presentation.adapter.SuperHeroAdapter

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val superheroAdapter = SuperHeroAdapter()


    val viewModel: SuperHeroListViewModel by lazy {
        SuperHeroListViewModel(
            GetSuperHeroesFeedUseCase(
                SuperHeroDataRepository(
                    XmlSuperHeroLocalDataSource(this),
                    SuperHeroRemoteDataSource(apiClient = ApiClient())
                ),
                BiographyDataRepository(
                    BiographyRemoteDataSource(apiClient = ApiClient())
                ),
                WorkDataRepository(
                    WorkRemoteDataSource(apiClient = ApiClient())
                )
            )
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
            it.superHero?.apply {
                superheroAdapter.submitList(this)
            }
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