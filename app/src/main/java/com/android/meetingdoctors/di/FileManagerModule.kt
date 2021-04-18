package com.android.meetingdoctors.di

import android.content.Context
import com.android.meetingdoctors.data.fileManager.FileManager
import com.android.meetingdoctors.data.fileManager.FileManagerImpl
import com.android.meetingdoctors.data.mapper.WordFileMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileManagerModule {

    @Singleton
    @Provides
    fun provideWordFileMapper(): WordFileMapper {
        return WordFileMapper()
    }

    @Singleton
    @Provides
    fun providesFileManager(@ApplicationContext app: Context): FileManager {
        return FileManagerImpl(app)
    }
}