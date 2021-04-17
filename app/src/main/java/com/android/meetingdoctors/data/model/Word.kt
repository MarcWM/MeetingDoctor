package com.android.meetingdoctors.data.model

data class Word(
    val name: String,
    val totalAppearances: Int = 0,
    val totalAppearancesPerFile: List<TotalAppearancesPerFile> = listOf()
)

data class TotalAppearancesPerFile(
    val fileName: String,
    val totalAppearances: Int = 0
)
