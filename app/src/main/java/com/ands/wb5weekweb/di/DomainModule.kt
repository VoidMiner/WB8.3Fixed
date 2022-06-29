package com.ands.wb5weekweb.di

import com.ands.wb5weekweb.repository.Repository
import com.ands.wb5weekweb.usecases.tinder.CreateVoteUseCase
import com.ands.wb5weekweb.usecases.tinder.GetCatUseCase
import com.ands.wb5weekweb.usecases.tinder.GetLikedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {



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



}