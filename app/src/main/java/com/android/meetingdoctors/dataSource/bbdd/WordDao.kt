package com.android.meetingdoctors.dataSource.bbdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.meetingdoctors.dataSource.model.WordEntity

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: WordEntity): Long

    @Query("SELECT * FROM words")
    suspend fun get(): List<WordEntity>

    @Query("SELECT filesWhereWordAppear FROM words")
    suspend fun getAllFilesAlreadySaved(): List<String>

    @Query("SELECT * FROM words WHERE name LIKE :query")
    suspend fun getWordsForCertainQuery(query: String): List<WordEntity>
}