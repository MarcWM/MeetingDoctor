package com.android.meetingdoctors.data.fileManager

import android.content.Context
import com.android.meetingdoctors.data.model.Word

class FileManagerImpl: FileManager {

    override fun readAllFileWords(context: Context, fileName: String): List<Word> {
        return readAndCollectWordsFromFile(context, fileName)
    }

    private fun readAndCollectWordsFromFile(context: Context, fileName: String): List<Word> {

        val listOfWords = arrayListOf<Word>()

        val linesList = context.assets.open(fileName).bufferedReader().readLines()


        linesList.forEach { line ->
            stringToWords(line).forEach { word ->
                listOfWords.add(
                    Word(
                        name = word
                    )
                )
            }
        }

        return listOfWords
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

}