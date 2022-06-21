package com.ands.wb5weekweb.viewmodels.tinder

import android.util.Log
import androidx.lifecycle.*
import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.VoteRequest
import com.ands.wb5weekweb.usecases.tinder.CreateVoteUseCase
import com.ands.wb5weekweb.usecases.tinder.GetCatUseCase
import com.ands.wb5weekweb.utils.Dao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TinderViewModel @Inject constructor(
    private val createVoteUseCase: CreateVoteUseCase,
    private val getCatUseCase: GetCatUseCase,
    private val dao: Dao
) : ViewModel() {

    private val _currentCat = MutableLiveData<Cat>()
    val currentCat: LiveData<Cat> = _currentCat
    private var currentImageId = UNDEFINED_ID

    var errorResponse = MutableLiveData<Boolean>(RESPONSE_NO_ERROR)

    init {
        getNewCat()
//        getNewCatKtor()
    }

    fun createVote(value: Int) = viewModelScope.launch {
        try {
            createVoteUseCase.createVote(voteRequest = makeVoteRequest(value = value))
                .let { response ->
                    if (response.isSuccessful && response.body() != null) {
                        Log.e("ViewModel", response.body().toString())
                        checkSuccess(response.body()?.message!!)
                    } else {
                        Log.e("TinderViewModel", "Error during request ${response.errorBody()}")
                    }
                }
        } catch (e: Exception) {
            Log.e("TinderViewModel", "Exception during request ${e.localizedMessage}")
        }
    }

    private fun checkSuccess(message: String) {
        if (message == SUCCESS_RESPONSE) {
            errorResponse.postValue(RESPONSE_NO_ERROR)
//            getNewCatKtor()
            getNewCat()
        } else {
            errorResponse.postValue(RESPONSE_ERROR)
        }
    }

    private fun makeVoteRequest(value: Int): VoteRequest {
        return VoteRequest(
            value = value,
            image_id = currentImageId,
            sub_id = "test123"
        )
    }

    private fun getNewCat() = viewModelScope.launch {
        try {
            getCatUseCase.getCat().let { response ->
                if (response.isSuccessful) {
                    response.body()?.first().let {
                        _currentCat.postValue(it)
                        currentImageId = it?.id ?: UNDEFINED_ID
                        cacheCat(it!!)
                    }
                } else {
                    Log.e("TinderViewModel", "Error during request ${response.errorBody()}")
                }
            }
        } catch (e: Exception) {
            getCachedCat()
            Log.e("VieModel", "exception during request ${e.localizedMessage}")
        }
    }

//    private fun getNewCatKtor() = viewModelScope.launch {
//
//        try {
//            getCatUseCase.getCatKtor().let { catList ->
//                _currentCat.postValue(catList.first())
//                cacheCat(catList.first())
//                currentImageId = catList.first().id
//                Log.e("KTOR", catList.first().toString())
//            }
//        } catch (e: Exception) {
//            Log.e("Ktor", "Exception during request ktor: ${e.localizedMessage}")
//            getCachedCat()
//        }
//    }

    private fun getCachedCat() = viewModelScope.launch {
        _currentCat.postValue(dao.getCatCache())
        Log.e("TAG", dao.getCatCache().toString())
    }

    private fun cacheCat(cat: Cat) = viewModelScope.launch {
        if (dao.isExists()) {
            dao.deleteCat()
            dao.insertCat(cat)
        }
        else
            dao.insertCat(cat)
    }

    companion object {
        const val UNDEFINED_ID = ""
        const val SUCCESS_RESPONSE = "SUCCESS"
        const val RESPONSE_ERROR = true
        const val RESPONSE_NO_ERROR = false
    }

}