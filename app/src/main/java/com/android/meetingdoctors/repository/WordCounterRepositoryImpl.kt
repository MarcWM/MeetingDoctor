package com.android.meetingdoctors.repository

import com.android.meetingdoctors.dataSource.cache.CacheDataSource
import com.android.meetingdoctors.dataSource.fileManager.FileManager
import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.repository.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Word Counter repository Implementation
 */
class WordCounterRepositoryImpl

constructor(
    private val fileManager: FileManager,
    private val wordDataSource: CacheDataSource
) : WordCounterRepository {

    override suspend fun getListOfWords(size: Int): Flow<DataState<List<Word>>> = flow {
        emit(DataState.Loading)
        val wordsList =  wordDataSource.getWordList(size)
        emit(DataState.Success(wordsList))
    }

    override suspend fun insertWordsFromNewFile(fileName: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)

        val listOfFilesAlreadySaved = wordDataSource.getFilesAlreadySaved()
        for (file in listOfFilesAlreadySaved) {
            if (file == fileName) {
                emit(DataState.Error(errorMessage = "File Already Saved!"))
                return@flow
            }
        }

        val listOfWords = fileManager.readAllFileWords(
            fileName = fileName,
            wordDataSource.getAllWordList()
        )
        wordDataSource.insertNewWordList(words = listOfWords)
        emit(DataState.Success(true))
    }

    override suspend fun search(query: String): Flow<DataState<List<Word>>>  = flow {
        emit(DataState.Loading)

        val listOfWords = wordDataSource.getWordsForCertainQuery("$query%")
        emit(DataState.Success(listOfWords))
    }

}