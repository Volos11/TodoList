package com.tetron.data_room.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.tetron.domain_models.NoteId
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId
import com.tetron.domain_models.Task
import com.tetron.domain_models.TaskWithoutId

@Entity(tableName = "note_table")
data class NoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val photo: String?,

) {

    companion object {
        fun mapToDbEntity(tagWithoutId: NoteId): NoteDbEntity = NoteDbEntity(
            name = tagWithoutId.name,
            photo = tagWithoutId.photo
        )
    }
}
