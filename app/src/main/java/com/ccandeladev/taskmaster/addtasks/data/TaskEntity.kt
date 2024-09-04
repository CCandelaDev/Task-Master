package com.ccandeladev.taskmaster.addtasks.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val taskString: String,
    var selected: Boolean = false
)
