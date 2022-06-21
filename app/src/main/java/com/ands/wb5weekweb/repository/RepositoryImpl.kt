package com.ands.wb5weekweb.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb5weekweb.api.ApiServiceCats
import com.ands.wb5weekweb.api.ApiServiceSuperHeroes
import com.ands.wb5weekweb.model.heroes.DotaHeroesResponse
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats
import com.ands.wb5weekweb.model.tinder.VoteRequest
import com.ands.wb5weekweb.model.tinder.VoteResponse
import com.ands.wb5weekweb.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import retrofit2.Response
import java.io.File


class RepositoryImpl(
    context: Context,
    private val apiServiceSuperHeroes: ApiServiceSuperHeroes,
    private val apiServiceCats: ApiServiceCats,
    private val client: HttpClient,
    private val dotaCacheStorage: DotaCacheStorage
) : Repository {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(SUPERHEROES_PREFERENCES, AppCompatActivity.MODE_PRIVATE)

    override suspend fun getSuperHeroes(): List<SuperHeroesResponse> {
        return apiServiceSuperHeroes.getSuperHeroes()
    }

    override suspend fun createVote(voteRequest: VoteRequest): Response<VoteResponse> {
        return apiServiceCats.createVote(body = voteRequest)
    }

    override suspend fun getCat(): Response<List<Cat>> {
        return apiServiceCats.getCat()
    }

    override suspend fun getLiked(subId: String): Response<List<LikedCats>> {
        return apiServiceCats.getLiked(subId = subId)
    }

    override suspend fun getCatKtor(): List<Cat> {
        return try {
            client.get(Constants.TINDER_KTOR_CATS)
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getLikedKtor(subId: String): List<LikedCats> {
        return try {
            client.get(Constants.TINDER_BASE_URL + "/v1/votes?sub_id=test123&&api_key=45b15940-98f7-4c78-a9aa-7283d14cc52e")
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override fun saveSuperHeroes(superHeroes: List<SuperHeroesResponse>) {
        val savedJsonHeroes = subjctsToJson(superHeroes)//положили в перменную json: String
        preferences.edit()
            .putString(PREF_SUPERHEROES_VALUE, savedJsonHeroes)//требует типа List, требует конвертер json
            .apply()
        Log.d(
            "SaveSuperHeroes",
            "path:/data/data/com.ands.wb5weekweb/shared_prefs/SUPERHEROES_PREFERENCE.xml"
        )
    }

    //Type сonverter
    private val gson = Gson()
    private fun subjctsToJson(subjcts: List<SuperHeroesResponse>?): String? {
        return if (subjcts == null || subjcts.isEmpty()) {
            null
        } else {
            gson.toJson(subjcts)
        }
    }

    private fun jsonToSubject(json: String?): List<SuperHeroesResponse> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<SuperHeroesResponse>>() {}.type
            gson.fromJson(json, type)
        }
    }

    override fun getSavedSuperHeroes(): List<SuperHeroesResponse> {
        val prefSavedHeroes = preferences.getString(PREF_SUPERHEROES_VALUE, "")
        Log.d("SavedSuperHeroes", "${jsonToSubject(prefSavedHeroes)}")
        return jsonToSubject(prefSavedHeroes)

    }

    override fun isCacheExist(): Boolean {
        val file = File(FILE_PATH)
        return file.exists() && getSavedSuperHeroes().isNotEmpty()
    }

    override fun cacheDotaHeroes(list: List<DotaHeroesResponse>) {
        dotaCacheStorage.saveDotaHeroesToFile(list = list)
    }

    override fun getCacheDotaHeroes(): List<DotaHeroesResponse> {
        return dotaCacheStorage.getDotaHeroesFromFile()
    }

    //private val preferencesListener = SharedPreferences.OnSharedPreferenceChangeListener{_, key-> }




    companion object {
        private const val SUPERHEROES_PREFERENCES = "SUPERHEROES_PREFERENCE"
        private const val PREF_SUPERHEROES_VALUE = "PREF_SUPERHEROES_VALUE"
        private const val FILE_PATH = "/data/data/com.ands.wb5weekweb/shared_prefs/${PREF_SUPERHEROES_VALUE}.xml"
    }
}
