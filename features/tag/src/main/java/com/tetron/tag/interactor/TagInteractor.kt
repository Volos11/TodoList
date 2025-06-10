package com.tetron.tag.interactor

import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId

interface TagInteractor {
    suspend fun getAllTags(): List<Tag>
    suspend fun addNewTag(tag: TagWithoutId)
    suspend fun deleteTag(tagId: Long)
}