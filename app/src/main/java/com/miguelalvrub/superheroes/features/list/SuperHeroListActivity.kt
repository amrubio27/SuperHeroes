package com.miguelalvrub.superheroes.features.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.miguelalvrub.superheroes.R
import com.miguelalvrub.superheroes.databinding.ActivityMainBinding
import com.miguelalvrub.superheroes.features.list.data.biography.BiographyDataRepository
import com.miguelalvrub.superheroes.features.list.data.biography.remote.BiographyRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.superhero.SuperHeroDataRepository
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.SuperHeroRemoteDataSource
import com.miguelalvrub.superheroes.features.list.data.work.WorkDataRepository
import com.miguelalvrub.superheroes.features.list.data.work.remote.WorkRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import com.miguelalvrub.superheroes.features.list.domain.SuperHeroRepository

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
        setContentView(R.layout.activity_main)
        //llamada al caso de uso y recojo lo que me devuelva y lo pinto por consola
        viewModel.loadSuperHeroesFeed()
    }


    private fun setupObservers() {
        val observer = Observer<SuperHeroListViewModel.UiState> {
            it.superHero?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun setupViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bindData(superHero: List<GetSuperHeroesFeedUseCase.Output>) {
        binding.apply {
            TODO()

        }
    }


}