package com.android.meetingdoctors.dataSource.fileManager

import android.content.Context
import com.android.meetingdoctors.dataSource.model.Word

/**
 * File Manager Implementation
 */
class FileManagerImpl

constructor(val context: Context): FileManager {

    @ExperimentalStdlibApi
    override fun readAllFileWords(fileName: String): List<Word> {
        return readAndCollectWordsFromFile(fileName)
    }

    @ExperimentalStdlibApi
    private fun readAndCollectWordsFromFile(fileName: String): List<Word> {

        val listOfWords = arrayListOf<Word>()

        val linesList = context.assets.open(fileName).bufferedReader().readLines()

        linesList.forEach { line ->
            stringToWords(line).forEach { word ->

                // Delete special chars
                val regex = "[^A-Za-z0-9]".toRegex()
                val wordWithoutSpecialChars = word.replace(regex = regex, "")

                // Search if the word is already in the list
                val wordIndex = searchIfWordIsAlreadyOnList(wordWithoutSpecialChars, listOfWords)

                if (wordIndex != null) {

                    // Increase total word appearances
                    listOfWords[wordIndex].totalAppearances += 1

                    // Increase word appearances for the given file
                    for (fileWhereWordAppear in listOfWords[wordIndex].filesWhereWordAppear) {

                        // If the file already exists, increase appearances. If not, add new
                        // file to th list
                        if (fileWhereWordAppear != fileName) {
                            listOfWords[wordIndex].filesWhereWordAppear.add(fileName)
                        }
                    }

                } else {

                    // Look if word is a number
                    if(wordWithoutSpecialChars.matches(Regex(".*[A-z].*"))) {

                        // Add new Word to list
                        listOfWords.add(
                            Word(
                                name = wordWithoutSpecialChars,
                                totalAppearances = 1,
                                filesWhereWordAppear = arrayListOf(fileName)
                            )
                        )
                    }
                }
            }
        }

        return listOfWords
    }

    private fun searchIfWordIsAlreadyOnList(wordToAdd: String, listOfWords: List<Word>): Int? {
        for ((index,word) in listOfWords.withIndex()) {
            if (word.name == wordToAdd || wordToAdd.contains(word.name, ignoreCase = true)) return index
        }
        return null
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

}