package com.android.meetingdoctors.dataSource.cache

import com.android.meetingdoctors.dataSource.bbdd.WordDao
import com.android.meetingdoctors.dataSource.mapper.CacheMapper
import com.android.meetingdoctors.dataSource.model.Word

class CacheDataSourceImpl
constructor(
    private val cacheMapper: CacheMapper,
    private val wordDao: WordDao
): CacheDataSource {

    override suspend fun insertNewWordList(words: List<Word>) {
        for (word in words) {
            wordDao.insert(cacheMapper.mapToEntity(word))
        }
    }

    override suspend fun getFilesAlreadySaved(): List<String> {
        return wordDao.getAllFilesAlreadySaved()
    }

    override suspend fun getWordList(): List<Word> {
        return cacheMapper.mapFromEntityList(wordDao.get())
    }

    override suspend fun getWordsForCertainQuery(query: String): List<Word> {
        return cacheMapper.mapFromEntityList(wordDao.getWordsForCertainQuery(query))
    }

}