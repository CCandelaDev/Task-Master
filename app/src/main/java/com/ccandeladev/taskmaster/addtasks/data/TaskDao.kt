package com.ccandeladev.taskmaster.addtasks.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    //Consulta personalizada
    @Query("SELECT * from TaskEntity")
    fun  getTasks(): Flow<List<TaskEntity>>


    @Insert
    suspend fun addTask(item: TaskEntity)
}