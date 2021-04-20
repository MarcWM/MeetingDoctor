package com.android.meetingdoctors.dataSource.mapper

import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.dataSource.model.WordEntity
import com.android.meetingdoctors.dataSource.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject constructor(): EntityMapper<WordEntity, Word> {

    override fun mapFromEntity(entity: WordEntity): Word {
        return Word(
            id = entity.id,
            name = entity.name,
            totalAppearances = entity.totalAppearances,
            filesWhereWordAppear = entity.filesWhereWordAppear
        )
    }

    override fun mapToEntity(model: Word): WordEntity {
        return WordEntity(
            name = model.name,
            totalAppearances = model.totalAppearances,
            filesWhereWordAppear = model.filesWhereWordAppear
        )
    }

    fun mapFromEntityList(entities: List<WordEntity>): List<Word> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(words: List<Word>): List<WordEntity> {
        return words.map { mapToEntity(it) }
    }
}