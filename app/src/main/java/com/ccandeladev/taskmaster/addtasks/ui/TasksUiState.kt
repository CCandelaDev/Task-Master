package com.ccandeladev.taskmaster.addtasks.ui

import com.ccandeladev.taskmaster.ui.model.TaskModel

sealed interface TasksUiState {

    object Loading: TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val task:List<TaskModel>): TasksUiState
}