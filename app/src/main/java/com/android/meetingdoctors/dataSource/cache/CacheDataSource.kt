package com.android.meetingdoctors.dataSource.cache

import com.android.meetingdoctors.dataSource.model.Word

/**
 * Cache Data Source
 */
interface CacheDataSource {

    /**
     * Insert new words into list
     * @param words: List of words
     */
    suspend fun insertNewWordList(words: List<Word>)

    /**
     * Get files that have been already saved
     */
    suspend fun getFilesAlreadySaved(): List<String>

    /**
     * Get Words from list with a limit
     * @param size Limit of list size
     */
    suspend fun getWordList(size: Int): List<Word>

    /**
     * Get all words from list saved, no limit
     */
    suspend fun getAllWordList(): List<Word>

    /**
     * Get words for a certain query
     */
    suspend fun getWordsForCertainQuery(query: String): List<Word>
}