package com.tetron.data_room.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tetron.data_room.room.entities.NoteDbEntity
import com.tetron.data_room.room.entities.TaskWithTag
import com.tetron.domain_models.NoteId


@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(task: NoteDbEntity)

    @Query("SELECT * FROM note_table")
    suspend fun getallNote(): List<NoteId>

    @Query("SELECT * FROM note_table WHERE id=:id_note")
    suspend fun getNoteId(id_note:Long): NoteId


    @Query("UPDATE note_table SET name=:name,photo=:photo WHERE id=:id_note")
    suspend fun changeNote(name:String,photo:String,id_note:Long)

    @Query("DELETE FROM note_table WHERE id=:id_note")
    suspend fun DeleteNote(id_note:Long)

}