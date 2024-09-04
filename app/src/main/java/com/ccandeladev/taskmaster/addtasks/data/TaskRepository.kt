package com.ccandeladev.taskmaster.addtasks.data

import com.ccandeladev.taskmaster.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

//Parte mas externa de data. Clase que hace las consultas a BD, Internet...
@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {


    //OJO: wuien llama a esto es el dominio y no deberia conocer lo que hay en data
    //No le pasamos TaskEntity sino TaskMOdel --> hay que hacer un mapeo

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks()
        .map { items -> items.map { TaskModel(it.id, it.taskString, it.selected) } }
        //Hago map del Flow--> me devuelve items-->Hago otro map

    //Añadir
    suspend fun add(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.taskString, taskModel.selected))
    }

}