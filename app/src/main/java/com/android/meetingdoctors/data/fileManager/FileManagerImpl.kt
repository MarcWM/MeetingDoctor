package com.android.meetingdoctors.data.fileManager

import android.content.Context
import com.android.meetingdoctors.data.model.TotalAppearancesPerFile
import com.android.meetingdoctors.data.model.Word
import com.android.meetingdoctors.data.fileManager.FileManager

/**
 * File Manager Implementation
 */
class FileManagerImpl: FileManager {

    override fun readAllFileWords(context: Context, fileName: String): List<Word> {
        return readAndCollectWordsFromFile(context, fileName)
    }

    private fun readAndCollectWordsFromFile(context: Context, fileName: String): List<Word> {

        val listOfWords = arrayListOf<Word>()

        val linesList = context.assets.open(fileName).bufferedReader().readLines()


        linesList.forEach { line ->
            stringToWords(line).forEach { word ->

                // Look if word is collectable


                // Search if the word is already in the list
                val wordIndex = searchIfWordIsAlreadyOnList(word, listOfWords)

                if (wordIndex != null) {

                    // Increase total word appearances
                    listOfWords[wordIndex].totalAppearances += 1

                    // Increase word appearances for the given file
                    for (totalAppearancesPerFile in listOfWords[wordIndex].totalAppearancesPerFile) {

                        // If the file already exists, increase appearances. If not, add new
                        // file to th list
                        if (totalAppearancesPerFile.fileName == fileName) {
                            totalAppearancesPerFile.totalAppearances += 1
                        } else {

                            // Initialize new File appearances
                            listOfWords[wordIndex].totalAppearancesPerFile.add(
                                TotalAppearancesPerFile(
                                    fileName = fileName,
                                    totalAppearances = 1
                                )
                            )
                        }
                    }

                } else {

                    // Add new Word to list
                    listOfWords.add(
                        Word(
                            name = word,
                            totalAppearances = 1,
                            totalAppearancesPerFile =  arrayListOf(
                                TotalAppearancesPerFile(
                                    fileName = fileName,
                                    totalAppearances = 1
                                )
                            )
                        )
                    )
                }
            }
        }

        return listOfWords
    }

    private fun searchIfWordIsAlreadyOnList(wordToAdd: String, listOfWords: List<Word>): Int? {
        for ((index,word) in listOfWords.withIndex()) {
            if (word.name == wordToAdd) return index
        }
        return null
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

}