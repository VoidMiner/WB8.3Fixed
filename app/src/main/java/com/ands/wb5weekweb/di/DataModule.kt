package com.ands.wb5weekweb.di

import android.content.Context
import androidx.room.Room
import com.ands.wb5weekweb.api.ApiServiceCats
import com.ands.wb5weekweb.api.ApiServiceSuperHeroes
import com.ands.wb5weekweb.repository.DotaCacheStorage
import com.ands.wb5weekweb.repository.DotaCacheStorageImpl
import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.repository.RepositoryImpl
import com.ands.wb5weekweb.utils.Constants
import com.ands.wb5weekweb.utils.Dao
import com.ands.wb5weekweb.utils.TinderDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule() {

    @Provides
    @Singleton
    fun provideApiService(): ApiServiceSuperHeroes {
        return Retrofit.Builder()
            .baseUrl(Constants.SUPER_HEROES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceSuperHeroes::class.java)
    }

    @Provides
    @Singleton
    fun privateApiServiceCats(): ApiServiceCats {
        return Retrofit.Builder()
            .baseUrl(Constants.TINDER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceCats::class.java)
    }

    @Provides
    @Singleton
    fun provideDotaCacheStorage(): DotaCacheStorage = DotaCacheStorageImpl()

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext context: Context,
        apiServiceSuperHeroes: ApiServiceSuperHeroes,
        apiServiceCats: ApiServiceCats,
        client: HttpClient,
        dotaCacheStorage: DotaCacheStorage
    ): Repository {
        return RepositoryImpl(
            context = context,
            apiServiceSuperHeroes = apiServiceSuperHeroes,
            apiServiceCats = apiServiceCats,
            client = client,
            dotaCacheStorage = dotaCacheStorage
        )
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json{ignoreUnknownKeys = true})
            }
        }
    }

    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: TinderDataBase): Dao {
        return appDatabase.getDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TinderDataBase {
        return Room.databaseBuilder(
            appContext,
            TinderDataBase::class.java,
            "cats.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}