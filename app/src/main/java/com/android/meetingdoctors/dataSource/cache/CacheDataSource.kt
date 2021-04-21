package com.android.meetingdoctors.dataSource.cache

import com.android.meetingdoctors.dataSource.model.Word

interface CacheDataSource {

    suspend fun insertNewWordList(words: List<Word>)

    suspend fun getFilesAlreadySaved(): List<String>

    suspend fun getWordList(size: Int): List<Word>

    suspend fun getAllWordList(): List<Word>

    suspend fun getWordsForCertainQuery(query: String): List<Word>
}