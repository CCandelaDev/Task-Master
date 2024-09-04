package com.ccandeladev.taskmaster.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.ccandeladev.taskmaster.addtasks.data.TaskDao
import com.ccandeladev.taskmaster.addtasks.data.TaskMasterDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Definir un módulo para proporcionar dependencias
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    //Proveer la interface del DAO
    @Provides
    fun provideTaskDao(taskMasterDataBase: TaskMasterDataBase): TaskDao{
        return taskMasterDataBase.taskDao()
    }

    // Proveer una instancia de una clase mediante el método @Provides
    @Provides
    @Singleton
    fun provideTaskMasterDataBase(@ApplicationContext appContext: Context): TaskMasterDataBase {
        return Room.databaseBuilder(
            appContext,
            TaskMasterDataBase::class.java,
            name = "TaskDatabase"
        ).build()
    }

}