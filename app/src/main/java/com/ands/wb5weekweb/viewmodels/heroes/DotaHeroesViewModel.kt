package com.ands.wb5weekweb.viewmodels.heroes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ands.wb5weekweb.model.heroes.DotaHeroesResponse
import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.usecases.CacheDotaHeroesUseCase
import com.ands.wb5weekweb.utils.Constants
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.*
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class DotaHeroesViewModel @Inject constructor(
    private val cacheDotaHeroesUseCase: CacheDotaHeroesUseCase
) : ViewModel() {

    private val _dotaHeroes = MutableLiveData<List<DotaHeroesResponse>>()
    val dotaHeroes: LiveData<List<DotaHeroesResponse>> = _dotaHeroes

    init {
        fetchJson()
    }

    private fun fetchJson() {

        val url = Constants.DOTA_BASE_URL + "/api/heroStats"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                try {

                    val body = response.body?.string() ?: return

                    val moshi = Moshi.Builder().build()
                    val listType = Types.newParameterizedType(List::class.java, DotaHeroesResponse::class.java)
                    val adapter: JsonAdapter<List<DotaHeroesResponse>> = moshi.adapter(listType)
                    val dotaHeroes = adapter.fromJson(body)

                    _dotaHeroes.postValue(dotaHeroes!!)
                    cacheDotaHeroesUseCase.saveDotaHeroes(dotaHeroes)

                } catch (e: Exception) {
                    Log.e("DotaHeroesViewModel", "exception during request ${e.localizedMessage}")
                } finally {
                    loadCache()
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println("API execute failed")
                loadCache()
            }
        })
    }

    fun loadCache() {
        try {
            _dotaHeroes.postValue(cacheDotaHeroesUseCase.getDotaHeroes())
        } catch (e: Exception) {
            Log.e("TAG", "Exception during request")
        }
    }


}