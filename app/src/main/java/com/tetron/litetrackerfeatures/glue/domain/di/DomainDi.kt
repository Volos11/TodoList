package com.tetron.litetrackerfeatures.glue.domain.di

import com.tetron.domain.usecase.AddNewTagUseCase
import com.tetron.domain.usecase.AddNewTaskUseCase
import com.tetron.domain.usecase.CheckTaskUseCase
import com.tetron.domain.usecase.DeleteTagUseCase
import com.tetron.domain.usecase.DeleteTaskUseCase
import com.tetron.domain.usecase.GetAllTagsUseCase
import com.tetron.domain.usecase.GetAllTasksUseCase
import com.tetron.domain.usecase.GetAllNoteUseCase
import com.tetron.domain.usecase.AddNote
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::AddNewTagUseCase)
    factoryOf(::AddNewTaskUseCase)
    factoryOf(::CheckTaskUseCase)
    factoryOf(::DeleteTagUseCase)
    factoryOf(::DeleteTaskUseCase)
    factoryOf(::GetAllTagsUseCase)
    factoryOf(::GetAllTasksUseCase)
    factoryOf(::AddNote)
    factoryOf(::GetAllNoteUseCase)
}