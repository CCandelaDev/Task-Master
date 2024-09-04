package com.ccandeladev.taskmaster.addtasks.domain

import com.ccandeladev.taskmaster.addtasks.data.TaskRepository
import com.ccandeladev.taskmaster.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    //Recibe un TaskModel para añadir
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }

}