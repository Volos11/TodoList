package com.tetron.data_room.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tetron.data_room.room.entities.NoteDbEntity
import com.tetron.data_room.room.entities.TagDbEntity

@Dao
interface TagDao {
    @Insert
    suspend fun addTag(tagDbEntity: TagDbEntity)



    @Query("SELECT * FROM tag_table")
    suspend fun getAllTags(): List<TagDbEntity>

    @Query("DELETE FROM tag_table WHERE id = :id")
    suspend fun deleteTag(id: Long)
}