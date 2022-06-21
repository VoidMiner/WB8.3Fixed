package com.ands.wb5weekweb.viewmodels.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DescriptionViewModel @Inject constructor(

): ViewModel() {

    private val heroes = mutableListOf<SuperHeroesResponse>()

    private val _currentCommonHero = MutableLiveData<CommonHeroesStats>()
    val currentCommonHero: LiveData<CommonHeroesStats> = _currentCommonHero

    //TODO сохранить список с героями в heroes
    //TODO сделать функцию для того, чтобы по id героя получать следующего/текущего/предыдущего
    //TODO героя, и впихивать его в  _currentCommonHero




    /*

              val commonHeroesStats = CommonHeroesStats(
                id = it.id.toString(),
                image = Constants.DOTA_BASE_URL + it.img,
                movementSpeed = it.moveSpeed.toString(),
                baseInt = it.baseInt.toString(),
                baseStrength = it.baseStrength.toString(),
                name = it.localizedName
            )

    */



}