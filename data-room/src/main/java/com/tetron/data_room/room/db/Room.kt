package com.tetron.data_room.room.db

import android.content.Context

class Room(context: Context) {
    val db: RoomStorage = BoulderRoomStorage().build(context)
}