package com.android.meetingdoctors.dataSource.cache

import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.dataSource.model.WordEntity

interface CacheDataSource {

    suspend fun insertNewWordList(words: List<Word>)

    suspend fun getFilesAlreadySaved(): List<String>

    suspend fun getWordList(): List<Word>

    suspend fun getWordsForCertainQuery(query: String): List<Word>
}