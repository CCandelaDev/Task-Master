package com.ccandeladev.taskmaster.addtasks.domain

import com.ccandeladev.taskmaster.addtasks.data.TaskRepository
import com.ccandeladev.taskmaster.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.update(taskModel = taskModel)
    }

}