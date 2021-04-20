package com.android.meetingdoctors.repository

import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.repository.state.DataState
import kotlinx.coroutines.flow.Flow

interface WordCounterRepository {

    suspend fun getListOfWords(): Flow<DataState<List<Word>>>

    suspend fun insertWordsFromNewFile(fileName: String): Flow<DataState<Boolean>>

    suspend fun search(query: String): Flow<DataState<List<Word>>>

    // suspend fun getWord(word: String): Word

}