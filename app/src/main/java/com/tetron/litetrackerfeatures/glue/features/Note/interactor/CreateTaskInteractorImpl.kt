package com.tetron.litetrackerfeatures.glue.features.Note.interactor

import com.tetron.create_task.interactor.CreateNoteIntercector
import com.tetron.domain.usecase.AddNote
import com.tetron.domain.usecase.GetAllNoteUseCase
import com.tetron.domain_models.NoteId

class CreateNoteInteractorImpl(
    private val addNote: AddNote,
    private val GetallNotes:GetAllNoteUseCase,
) : CreateNoteIntercector {

    override suspend fun addNewNote(task: NoteId, tagId: Long?) {
        addNote.execute(task)
    }

    override suspend fun ChangeNote(task: NoteId, tagId: Long?): Int {
        addNote.change(task)
        return 0
    }

    override suspend fun DeleteNote(id: Long) {
        addNote.delete(id)
    }

    override suspend fun getNoteId(id: Long): NoteId {

        return  GetallNotes.executeid(id)
    }


    override suspend fun GetAllNote(): List<NoteId> =
        GetallNotes.execute()


}