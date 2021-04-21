package com.android.meetingdoctors.dataSource.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.meetingdoctors.dataSource.model.WordEntity

@Database(entities = [WordEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class WordDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        const val DATABASE_NAME: String = "word_db"
    }
}