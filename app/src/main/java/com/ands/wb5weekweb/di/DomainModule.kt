package com.ands.wb5weekweb.di

import android.content.Context
import androidx.room.Room
import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.usecases.CacheDotaHeroesUseCase
import com.ands.wb5weekweb.usecases.tinder.CreateVoteUseCase
import com.ands.wb5weekweb.usecases.tinder.GetCatUseCase
import com.ands.wb5weekweb.usecases.tinder.GetLikedUseCase
import com.ands.wb5weekweb.usecases.SuperHeroesUseCase
import com.ands.wb5weekweb.utils.Dao
import com.ands.wb5weekweb.utils.TinderDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetHeroesUseCase(repository: Repository): SuperHeroesUseCase {
        return SuperHeroesUseCase(repository = repository)
    }

    @Provides
    fun provideGetCatUseCase(repository: Repository): GetCatUseCase {
        return GetCatUseCase(repository = repository)
    }

    @Provides
    fun provideCreateVoteUseCase(repository: Repository): CreateVoteUseCase {
        return CreateVoteUseCase(repository = repository)
    }

    @Provides
    fun provideGetLikedUseCase(repository: Repository): GetLikedUseCase {
        return GetLikedUseCase(repository = repository)
    }

    @Provides
    fun provideCacheHeroesUseCase(repository: Repository): CacheDotaHeroesUseCase {
        return CacheDotaHeroesUseCase(repository = repository)
    }

}