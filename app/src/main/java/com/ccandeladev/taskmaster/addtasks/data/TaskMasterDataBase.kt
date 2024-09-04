package com.ccandeladev.taskmaster.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskMasterDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}