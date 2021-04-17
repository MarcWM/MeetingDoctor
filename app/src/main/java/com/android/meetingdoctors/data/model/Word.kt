package com.android.meetingdoctors.data.model

data class Word(
    val name: String,
    var totalAppearances: Int = 0,
    val totalAppearancesPerFile: ArrayList<TotalAppearancesPerFile> = arrayListOf()
)

data class TotalAppearancesPerFile(
    val fileName: String,
    var totalAppearances: Int = 0
)
