package com.ccandeladev.taskmaster.addtasks.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {

    var showDialog by mutableStateOf(false)
        private set

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

        Log.i("CAYE", "Prueba Task")
    }

    //Function to show dialog when click
    fun onShowDialogClick(){
        showDialog = true
    }

}