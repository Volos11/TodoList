package com.tetron.domain.repository


import com.tetron.domain_models.NoteId
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId
import com.tetron.domain_models.Task
import com.tetron.domain_models.TaskWithoutId

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun getAllTags(): List<Tag>
    suspend fun addNewTask(newTask: TaskWithoutId)
    suspend fun addNewTag(newTag: TagWithoutId)
    suspend fun deleteTaskById(taskId: Long)
    suspend fun deleteTagById(tagId: Long)
    suspend fun updateStatusTaskById(taskId: Long, status: Boolean)
    suspend fun addNote(newTask: NoteId)
    suspend fun changeNote(newTask: NoteId):Int
    suspend fun DeleteNote(id: Long)
    suspend fun GetAllNote(): List<NoteId>
    suspend fun GetNoteId(id:Long): NoteId
}