package com.android.meetingdoctors.repository

import com.android.meetingdoctors.data.fileManager.FileManager
import com.android.meetingdoctors.data.mapper.WordFileMapper
import com.android.meetingdoctors.data.model.Word

class WordCounterRepositoryImpl

constructor(
    private val fileManager: FileManager,
    private val wordFileMapper: WordFileMapper
) : WordCounterRepository {

    override fun getListOfWords(): List<Word> {
        return fileManager.readAllFileWords("alice29.txt")
    }

}