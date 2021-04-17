package com.android.meetingdoctors.di

import com.android.meetingdoctors.data.fileManager.FileManager
import com.android.meetingdoctors.repository.WordCounterRepository
import com.android.meetingdoctors.repository.WordCounterRepositoryImpl
import com.android.meetingdoctors.data.mapper.WordFileMapper
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
        wordFileMapper: WordFileMapper
    ): WordCounterRepository {
        return WordCounterRepositoryImpl(
            fileManager = fileManager,
            wordFileMapper = wordFileMapper
        )
    }
}