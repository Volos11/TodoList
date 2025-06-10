package com.tetron.domain.usecase

import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.NoteId
import com.tetron.domain_models.TagWithoutId
import com.tetron.domain_models.TaskShort

class AddNote(
    private val repository: TaskRepository
) {
    suspend fun execute(task: NoteId) {
        repository.addNote(
            task
        )
    }
    suspend fun delete(id: Long) {
        repository.DeleteNote(
            id
        )
    }
    suspend fun change(task: NoteId) {
        repository.changeNote(
            task
        )
    }


}


