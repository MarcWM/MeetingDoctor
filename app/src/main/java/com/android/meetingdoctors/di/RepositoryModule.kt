package com.android.meetingdoctors.di

import com.android.meetingdoctors.dataSource.cache.CacheDataSource
import com.android.meetingdoctors.dataSource.fileManager.FileManager
import com.android.meetingdoctors.repository.WordCounterRepository
import com.android.meetingdoctors.repository.WordCounterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesWordRepository(
        fileManager: FileManager,
        wordDataSource: CacheDataSource
    ): WordCounterRepository {
        return WordCounterRepositoryImpl(
            fileManager = fileManager,
            wordDataSource = wordDataSource
        )
    }
}