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

    }

}