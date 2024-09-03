package com.ccandeladev.taskmaster.ui.model

import android.util.Log

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    val taskString: String,
    var selected: Boolean = false
)
