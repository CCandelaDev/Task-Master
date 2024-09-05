package com.ccandeladev.taskmaster.addtasks.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccandeladev.taskmaster.addtasks.domain.AddTaskUseCase
import com.ccandeladev.taskmaster.addtasks.domain.DeleteTaskUseCase
import com.ccandeladev.taskmaster.addtasks.domain.GetTasksUseCase
import com.ccandeladev.taskmaster.addtasks.domain.UpdateTaskUseCase
import com.ccandeladev.taskmaster.addtasks.ui.TasksUiState.Success
import com.ccandeladev.taskmaster.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    // StateFlow---> consume nuestros estados de uso---> injecto casos de uso
    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { TasksUiState.Error(it) } //sI FALLA
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksUiState.Loading)
    //Coje contenido StateFlow y por cada uno lo convierte en un success(ver video)

    var showDialog by mutableStateOf(false)
        private set

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val task: List<TaskModel> = _tasks


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
        //_tasks.add(TaskModel(taskString = task))--> Ya no sirve con el Flow

        //Flujo de datos
        viewModelScope.launch {
            addTaskUseCase(TaskModel(taskString = task))
        }

    }

    //Function to show dialog when click
    fun onShowDialogClick() {
        showDialog = true
    }

    fun onCheckBoxChange(taskModel: TaskModel) {
        //ACTUALIZAR CHECK
//        val index = _tasks.indexOf(taskModel)//busco el indice
//        //Cojo la posición y crea mismo objeto con el valor selected cambado(click checBox)
//        _tasks[index] = _tasks[index].let { it.copy(selected = !it.selected) }
//
//        //let es una función de extensión que se utiliza para ejecutar un bloque de código
//        // en un contexto específico y devolver el resultado de la última expresión
//        // dentro de ese bloque

        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }//copy--> copia el objeto y modifico solo selected al valor contrario
    }

    fun onItemRemove(taskModel: TaskModel) {
        //BORRAR ITEM
//        val task = _tasks.find { it.id == taskModel.id }
//        //Busca en las tareas, la tarea con el id(tasModel.id) que le paso dentro del listado de ids(it.id)
//        _tasks.remove(task)

        viewModelScope.launch {
            deleteTaskUseCase(taskModel = taskModel)
            //Sabe la que hay que borrar por @PrimaryKey --> es única
        }
    }

}