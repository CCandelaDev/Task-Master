package com.ccandeladev.taskmaster.addtasks.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ccandeladev.taskmaster.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {

    var showDialog by mutableStateOf(false)
        private set

    private val _tasks = mutableStateListOf<TaskModel>()
    val task: List<TaskModel> = _tasks


    //Function for close onDismiss
    fun onDialogClose() {
        showDialog = false
    }

    /**
     * Function to create a task
     * @param task
     */
    fun onTaskCreate(task: String) {
        showDialog = false // Close Dialog
        _tasks.add(TaskModel(taskString = task))


        //Log.i("CAYE", "Prueba Task")
    }

    //Function to show dialog when click
    fun onShowDialogClick() {
        showDialog = true
    }

    fun onCheckBoxChange(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)//busco el indice
        //Cojo la posición y crea mismo objeto con el valor selected cambado(click checBox)
        _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }

        //let es una función de extensión que se utiliza para ejecutar un bloque de código
        // en un contexto específico y devolver el resultado de la última expresión
        // dentro de ese bloque
    }

    fun onItemRemove(taskModel: TaskModel) {
            val task = _tasks.find{it.id == taskModel.id}
        //Busca en las tareas, la tarea con el id(tasModel.id) que le paso dentro del listado de ids(it.id)
        _tasks.remove(task)
    }

}