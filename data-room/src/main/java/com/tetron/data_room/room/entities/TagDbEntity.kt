package com.tetron.data_room.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId

@Entity(tableName = "tag_table")
data class TagDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
) {
    fun mapToTag() : Tag = Tag(id = this.id, name = this.name)

    companion object {
        fun mapToDbEntity(tagWithoutId: TagWithoutId): TagDbEntity = TagDbEntity(
            name = tagWithoutId.name
        )
    }
}
