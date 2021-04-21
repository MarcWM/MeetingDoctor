package com.android.meetingdoctors.dataSource.fileManager

import com.android.meetingdoctors.dataSource.model.Word

/**
 * File Manager Interface
 */
interface FileManager {

    /**
     * Read all words that appear in file, returning a list of [Word] type
     */
    fun readAllFileWords(fileName: String, wordListCached: List<Word>?): List<Word>
}