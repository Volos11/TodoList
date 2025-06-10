package com.tetron.litetrackerfeatures.glue.data_room.di

import com.tetron.data_room.repository.TaskRepositoryImpl
import com.tetron.data_room.room.db.Room
import com.tetron.domain.repository.TaskRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataRoomModule = module {
    singleOf(::Room)
    singleOf(::TaskRepositoryImpl) { bind <TaskRepository>() }
}