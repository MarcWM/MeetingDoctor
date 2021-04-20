package com.android.meetingdoctors.dataSource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "totalAppearances")
    var totalAppearances: Int = 0,

    @ColumnInfo(name = "filesWhereWordAppear")
    var filesWhereWordAppear: ArrayList<String> = arrayListOf()
)
