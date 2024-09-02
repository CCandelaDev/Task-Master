package com.ccandeladev.taskmaster.addtasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ccandeladev.taskmaster.ui.theme.TaskMasterTheme

@Composable
fun TasksScreen() {
    Box(Modifier.fillMaxSize()) {
        FabDialog(Modifier.align(Alignment.BottomEnd))
        AddTasksDialog(show = true, onDismiss = {}, onTaskAdded = {})
    }
}


@Composable
fun FabDialog(modifier: Modifier) {

    //Al pulsar se muestra el dialogo
    SmallFloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .padding(18.dp),
        shape = RoundedCornerShape(20.dp),
        containerColor = Color.White
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
    }
}

/**
 * @param show mostar o ocultar el dialogo
 * @param onDismiss hace que se oculte al pulsar fuera
 * @param onTaskAdded Para devolver una string y crear la tarea
 */
@Composable
fun AddTasksDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {

    var myTask by rememberSaveable {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = { onDismiss }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
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
                    onTaskAdded(myTask) //Le pasamos la tarea
                }, Modifier.fillMaxWidth()) {
                    Text(text = "Añadir Tarea")
                }
            }

        }
    }

}


@Preview
@Composable
fun Preview() {
    TaskMasterTheme {
        TasksScreen()
    }
}