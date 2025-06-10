package com.tetron.data_room.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tetron.data_room.room.entities.NoteDbEntity
import com.tetron.data_room.room.entities.TaskDbEntity
import com.tetron.data_room.room.entities.TaskWithTag

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: TaskDbEntity)


    @Query("UPDATE task_table SET status = :status WHERE id = :taskId")
    suspend fun updateTaskStatusById(taskId: Long, status: Boolean)

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<TaskDbEntity>

    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)

    @Query("SELECT task_table.id AS taskId, task_table.title AS taskTitle, task_table.description AS taskDescription, " +
            "task_table.dateMill AS taskDateMill, task_table.status AS taskStatus, task_table.tagId AS taskIdTag, " +
            "tag_table.name AS tagName " +
            "FROM task_table LEFT JOIN tag_table ON task_table.tagId = tag_table.id " +
            "ORDER BY task_table.dateMill ASC")
    suspend fun getAllTasksWithTagsSorted(): List<TaskWithTag>



}