package com.tetron.data_room.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tetron.data_room.room.entities.NoteDbEntity
import com.tetron.data_room.room.entities.TagDbEntity
import com.tetron.data_room.room.entities.TaskDbEntity

@Database(
    version = 3,
    entities = [
        TagDbEntity::class,
        TaskDbEntity::class,
        NoteDbEntity::class
    ]
)
abstract class RoomStorage : RoomDatabase() {
    abstract fun getTagDao() : TagDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getNoteDao(): NoteDao
}