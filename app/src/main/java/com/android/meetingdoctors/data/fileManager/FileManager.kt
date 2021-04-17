package com.android.meetingdoctors.data.fileManager

import android.content.Context
import com.android.meetingdoctors.data.model.Word

/**
 * File Manager Interface
 */
interface FileManager {

    /**
     * Read all words that appear in file, returning a list of [Word] type
     */
    fun readAllFileWords(context: Context, fileName: String): List<Word>
}