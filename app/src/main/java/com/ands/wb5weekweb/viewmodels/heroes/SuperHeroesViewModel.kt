package com.ands.wb5weekweb.viewmodels.heroes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ands.wb5weekweb.fragments.heroes.DescriptionFragment
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import com.ands.wb5weekweb.usecases.SuperHeroesUseCase
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SuperHeroesViewModel @Inject constructor(
    private val superHeroesUseCase: SuperHeroesUseCase,
) : ViewModel() {

    init {
        chooseDataStore()
    }

    private val _superHero = MutableLiveData<List<SuperHeroesResponse>>()
    val superHeroes: LiveData<List<SuperHeroesResponse>> = _superHero

    private fun chooseDataStore() {
        if (superHeroesUseCase.isCacheExist()) {
            loadCache()
        } else {
            loadHeroes()
        }
    }

    private fun loadHeroes() = viewModelScope.launch {
        try {
            superHeroesUseCase.getHeroes().let { response ->
                Log.d("HEROESRESPONCE", "$response")
                _superHero.postValue(response)
                superHeroesUseCase.saveHeroes(response)
            }
        } catch (e: Exception) {
            Log.d("tag", "Exception during request: ${e.localizedMessage}")
        } finally {
            loadCache()
        }
    }

    private fun loadCache() {
        val cachedHeroes = superHeroesUseCase.loadCacheHeroesList()
        _superHero.postValue(cachedHeroes)
    }

}