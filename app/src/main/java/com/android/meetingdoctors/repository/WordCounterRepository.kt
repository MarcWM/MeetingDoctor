package com.android.meetingdoctors.repository

import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.repository.state.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Word counter repository
 */
interface WordCounterRepository {

    /**
     * Get list of words with a limit
     * @param size: Limit of words to show
     */
    suspend fun getListOfWords(size: Int): Flow<DataState<List<Word>>>

    /**
     * Insert words from a given file
     * @param fileName: Name of the file
     */
    suspend fun insertWordsFromNewFile(fileName: String): Flow<DataState<Boolean>>

    /**
     * Search for string that match with the given query
     * @param query: String to search
     */
    suspend fun search(query: String): Flow<DataState<List<Word>>>
}