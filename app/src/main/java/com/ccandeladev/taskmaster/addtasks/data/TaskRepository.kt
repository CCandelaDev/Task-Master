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
    suspend fun add(taskModel: TaskModel) {  //al recibir TaskModel--> hay que hacer mapeo
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.taskString, taskModel.selected))
    }


    suspend fun update(taskModel: TaskModel) {
        taskDao.updateTask(TaskEntity(taskModel.id, taskModel.taskString, taskModel.selected))
    }

    suspend fun delete(taskModel: TaskModel) {
        //taskDao.deleteTask(TaskEntity(taskModel.id, taskModel.taskString, taskModel.selected))
        taskDao.deleteTask(taskModel.toData())  //--> Aplicando funcion extensión
    }

}


//Funcion extensión
fun TaskModel.toData(): TaskEntity {
    return TaskEntity(this.id, this.taskString, this.selected)
}