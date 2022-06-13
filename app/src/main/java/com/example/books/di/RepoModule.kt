package com.example.books.di

import com.example.books.network.ApiService
import com.example.books.repository.VolumeEndPoint
import com.example.books.repository.VolumeRepository
import com.example.books.repository.VolumeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideVolumeRepository(): VolumeRepository {
        return VolumeRepositoryImp(ApiService<VolumeEndPoint>())
    }
}