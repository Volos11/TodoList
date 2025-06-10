package com.tetron.data_room.repository

import com.tetron.data_room.room.db.NoteDao
import com.tetron.data_room.room.db.Room
import com.tetron.data_room.room.db.TagDao
import com.tetron.data_room.room.db.TaskDao
import com.tetron.data_room.room.entities.NoteDbEntity
import com.tetron.data_room.room.entities.TagDbEntity
import com.tetron.data_room.room.entities.TaskDbEntity
import com.tetron.domain.repository.TaskRepository
import com.tetron.domain_models.NoteId
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId
import com.tetron.domain_models.Task
import com.tetron.domain_models.TaskWithoutId

class TaskRepositoryImpl(room: Room): TaskRepository {
    private val tagDao: TagDao = room.db.getTagDao()
    private val taskDao: TaskDao = room.db.getTaskDao()
    private val Notedao: NoteDao = room.db.getNoteDao()

    override suspend fun getAllTasks(): List<Task> =
        taskDao.getAllTasksWithTagsSorted().map { it.mapToTask() }

    override suspend fun getAllTags(): List<Tag> =
        tagDao.getAllTags().map { it.mapToTag() }

    override suspend fun addNewTask(newTask: TaskWithoutId) =
        taskDao.addTask(TaskDbEntity.mapToDbEntity(newTask))

    override suspend fun addNewTag(newTag: TagWithoutId) =
        tagDao.addTag(TagDbEntity.mapToDbEntity(newTag))

    override suspend fun deleteTaskById(taskId: Long) =
        taskDao.deleteTaskById(taskId)

    override suspend fun updateStatusTaskById(taskId: Long, status: Boolean) {
        taskDao.updateTaskStatusById(taskId, status)
    }

    override suspend fun addNote(newTask: NoteId) {
        Notedao.addNote(NoteDbEntity.mapToDbEntity(newTask))
    }

    override suspend fun changeNote(newTask: NoteId): Int {
        Notedao.changeNote(newTask.name, newTask.photo?:"" ,newTask.id!!)
        return 0
    }

    override suspend fun DeleteNote(id: Long) {
        Notedao.DeleteNote(id)
    }

    override suspend fun GetAllNote(): List<NoteId> =
        Notedao.getallNote()

    override suspend fun GetNoteId(id:Long): NoteId =
        Notedao.getNoteId(id)



    override suspend fun deleteTagById(tagId: Long)  =
        tagDao.deleteTag(tagId)
}