package com.android.meetingdoctors.repository

import com.android.meetingdoctors.data.model.Word

interface WordCounterRepository {

    fun getListOfWords(): List<Word>

    // suspend fun search(query: String): List<Word>

    // suspend fun getWord(word: String): Word

}