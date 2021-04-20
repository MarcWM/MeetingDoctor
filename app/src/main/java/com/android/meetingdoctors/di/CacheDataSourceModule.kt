package com.android.meetingdoctors.di

import android.content.Context
import androidx.room.Room
import com.android.meetingdoctors.dataSource.cache.CacheDataSource
import com.android.meetingdoctors.dataSource.cache.CacheDataSourceImpl
import com.android.meetingdoctors.dataSource.mapper.CacheMapper
import com.android.meetingdoctors.dataSource.bbdd.WordDao
import com.android.meetingdoctors.dataSource.bbdd.WordDatabase
import com.android.meetingdoctors.dataSource.fileManager.FileManager
import com.android.meetingdoctors.dataSource.fileManager.FileManagerImpl
import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.dataSource.model.WordEntity
import com.android.meetingdoctors.dataSource.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheDataSourceModule {

    @Singleton
    @Provides
    fun provideCacheMapper(): EntityMapper<WordEntity, Word> {
        return CacheMapper()
    }

    @Singleton
    @Provides
    fun provideWordCounterDatabase(@ApplicationContext context: Context): WordDatabase {
        return Room
            .databaseBuilder(
                context,
                WordDatabase::class.java,
                WordDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWordCounterDao(wordDatabase: WordDatabase): WordDao {
        return wordDatabase.wordDao()
    }

    @Singleton
    @Provides
    fun providesDataSource(
        cacheMapper: CacheMapper,
        wordDao: WordDao
    ): CacheDataSource {
        return CacheDataSourceImpl(cacheMapper, wordDao)
    }
}