package com.ccandeladev.taskmaster.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(),
    val taskString: String,
    var selected: Boolean = false
)
