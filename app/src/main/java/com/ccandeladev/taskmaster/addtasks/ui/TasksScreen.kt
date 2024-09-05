package com.ccandeladev.taskmaster.addtasks.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.ccandeladev.taskmaster.ui.model.TaskModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog = tasksViewModel.showDialog

    val lifeCycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<TasksUiState>(
        initialValue = TasksUiState.Loading,
        key1 = lifeCycle,
        key2 = tasksViewModel
    ) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            tasksViewModel.uiState.collect { value = it }
        }
    }

    when (uiState) {
        is TasksUiState.Error -> {}
        TasksUiState.Loading -> {
            CircularProgressIndicator()
        }

        is TasksUiState.Success -> {
            Box(Modifier.fillMaxSize()) {
                FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)

                AddTasksDialog(
                    show = showDialog,
                    onDismiss = { tasksViewModel.onDialogClose() },
                    onTaskAdded = { tasksViewModel.onTaskCreate(it) })

                //TasksList(tasksViewModel)
                TasksList((uiState as TasksUiState.Success).task, tasksViewModel)
            }
        }
    }


}

/**
 * Function to list the tasks
 */
@Composable
fun TasksList(task: List<TaskModel>, tasksViewModel: TasksViewModel) {

    //val myTaskList: List<TaskModel> = tasksViewModel.task --> Lo sustituye el flow-->los datos son los de la BD

    LazyColumn {
        items(task, key = { it.id }) { task ->
            // ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
            ItemTask(taskModel = task, tasksViewModel = tasksViewModel)
        }

    }
}


@Composable
fun ItemTask(taskModel: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    tasksViewModel.onItemRemove(taskModel)
                })


            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        border = BorderStroke(2.dp, Color.Cyan)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = taskModel.taskString,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Red)
                    .padding(start = 8.dp),
                color = Color.White,

                )
            Checkbox(
                checked = taskModel.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxChange(taskModel) })
        }

    }
}


@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {

    //Al pulsar se muestra el dialogo
    SmallFloatingActionButton(
        onClick = {
            //Mostrar dialogo
            tasksViewModel.onShowDialogClick()
        },
        modifier = modifier
            .padding(bottom = 50.dp, end = 20.dp),
        shape = RoundedCornerShape(20.dp),
        containerColor = Color.White
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}

/**
 * @param show mostar o ocultar el dialogo
 * @param onDismiss hace que se oculte al pulsar fuera
 * @param onTaskAdded Para crear la tarea (devuelve String)
 */
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {

    var myTask by rememberSaveable {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Añade tu Tarea",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    singleLine = true,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)//Le pasamos la tarea
                    myTask = "" //Vacio el texto
                }, Modifier.fillMaxWidth()) {
                    Text(text = "Añadir Tarea")
                }
            }

        }
    }

}


