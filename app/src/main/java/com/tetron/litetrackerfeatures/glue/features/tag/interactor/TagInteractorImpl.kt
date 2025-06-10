package com.tetron.litetrackerfeatures.glue.features.tag.interactor

import com.tetron.domain.usecase.AddNewTagUseCase
import com.tetron.domain.usecase.DeleteTagUseCase
import com.tetron.domain.usecase.GetAllTagsUseCase
import com.tetron.domain_models.Tag
import com.tetron.domain_models.TagWithoutId
import com.tetron.tag.interactor.TagInteractor

class TagInteractorImpl(
    private val getAllTagsUseCase: GetAllTagsUseCase,
    private val addNewTagUseCase: AddNewTagUseCase,
    private val deleteTagUseCase: DeleteTagUseCase
) : TagInteractor {
    override suspend fun getAllTags(): List<Tag> =
        getAllTagsUseCase.execute()

    override suspend fun addNewTag(tag: TagWithoutId) =
        addNewTagUseCase.execute(tag)

    override suspend fun deleteTag(tagId: Long) =
        deleteTagUseCase.execute(tagId)
}