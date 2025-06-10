package com.tetron.litetrackerfeatures

import android.app.Application
import com.tetron.litetrackerfeatures.glue.features.all_tasks.di.allTasksFeatureModule
import com.tetron.litetrackerfeatures.glue.features.create_task.di.createTaskFeatureModule
import com.tetron.litetrackerfeatures.glue.data_room.di.dataRoomModule
import com.tetron.litetrackerfeatures.glue.domain.di.domainModule
import com.tetron.litetrackerfeatures.glue.features.Note.di.CreateNote
import com.tetron.litetrackerfeatures.glue.features.Note.di.GetNoteDi


import com.tetron.litetrackerfeatures.glue.features.tag.di.tagFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                dataRoomModule,
                domainModule,
                allTasksFeatureModule,
                createTaskFeatureModule,
                tagFeatureModule,
                CreateNote,
                GetNoteDi

            )
        }
    }
}