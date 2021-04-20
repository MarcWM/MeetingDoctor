package com.android.meetingdoctors.dataSource.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Word(
    val id: Int? = null,
    val name: String,
    var totalAppearances: Int = 0,
    val filesWhereWordAppear: ArrayList<String> = arrayListOf()
)