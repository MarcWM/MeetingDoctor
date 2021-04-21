package com.android.meetingdoctors.dataSource.model

/**
 * Word data class
 *
 * @param id: Identifier
 * @param name: Word saved
 * @param totalAppearances: RTimes word appears
 * @param filesWhereWordAppear: files where word appear
 */
data class Word(
    val id: Int? = null,
    val name: String,
    var totalAppearances: Int = 0,
    val filesWhereWordAppear: ArrayList<String> = arrayListOf()
)