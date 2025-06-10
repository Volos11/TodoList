package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.NoteId

class GetAllNoteUseCase(
    private val repository: TaskRepository
) {
    suspend fun execute() : List<NoteId> =
        repository.GetAllNote()

    suspend fun executeid(id:Long) : NoteId =
        repository.GetNoteId(id)

    suspend fun delete(id:Long) : Unit =
        repository.DeleteNote(id)
}