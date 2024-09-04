package com.ccandeladev.taskmaster.addtasks.domain

import com.ccandeladev.taskmaster.addtasks.data.TaskRepository
import com.ccandeladev.taskmaster.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//CASO DE USO-->Recuperar las tareas
//Necesito injectar el repositorio que es la puerta de entrada a data
class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.tasks
    }

    /*
    invoke() --> permite que la instancia de GetTasksUseCase
    se pueda llamar directamente como si fuera una función, por ejemplo, getTasksUseCase()

    Flow es parte de la librería de coroutines de Kotlin y se utiliza para manejar flujos de datos asincrónicos y reactivos.
    */

}