package com.tetron.data_room.room.db

import android.content.Context
import androidx.room.Room


internal class BoulderRoomStorage {
    fun build(context: Context): RoomStorage {
        return Room.databaseBuilder(context, RoomStorage::class.java, "RoomStorage.db").fallbackToDestructiveMigration().build()
    }
}