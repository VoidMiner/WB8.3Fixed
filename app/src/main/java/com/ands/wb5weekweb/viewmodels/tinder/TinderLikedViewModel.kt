package com.ands.wb5weekweb.viewmodels.tinder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats
import com.ands.wb5weekweb.usecases.tinder.GetLikedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class TinderLikedViewModel @Inject constructor(
    private val getLikedUseCase: GetLikedUseCase
) : ViewModel() {

    private val likedList = mutableListOf<LikedCats>()
    private val _likedCats = MutableLiveData<List<LikedCats>>()
    val likedCats: LiveData<List<LikedCats>> = _likedCats

    init {
        loadFavourites("test123")
        loadLikedKtor("test123")
    }

    private fun loadFavourites(subId: String) = viewModelScope.launch {
        try {
            getLikedUseCase.getLiked(subId = subId).let { response ->
                if (response.isSuccessful) {
                    Log.e("ViewModel", response.body().toString())

                    if (response.body() == null) return@launch

                    addAllLikedToList(response.body()!!)

                    _likedCats.postValue(likedList)
                } else {
                    Log.d("ViewModel", "Error during request: ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Log.d("ViewModel", "Exception during request: ${e.localizedMessage}")
        }
    }

    private fun loadLikedKtor(subId: String) = viewModelScope.launch {
        try {
            getLikedUseCase.getLikedKtor(subId = subId).let { response ->

                addAllLikedToList(response)
                _likedCats.postValue(likedList)
                Log.e("Ktor", response.toString())

            }
        } catch (e: Exception) {
            Log.d("ViewModel", "Exception during request: ${e.localizedMessage}")
        }
    }

    private fun addAllLikedToList(list: List<LikedCats>) {
        for (i in list) {
            if (i.value == 1) likedList.add(i)
        }
    }

}