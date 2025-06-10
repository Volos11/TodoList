package com.tetron.create_task.interactor

import com.tetron.domain_models.NoteId

interface CreateNoteIntercector {
    suspend fun addNewNote(task : NoteId, tagId: Long?)
    suspend fun ChangeNote(task : NoteId, tagId: Long?):Int
    suspend fun DeleteNote(id : Long)
    suspend fun GetAllNote(): List<NoteId>
    suspend fun getNoteId(id:Long): NoteId
}