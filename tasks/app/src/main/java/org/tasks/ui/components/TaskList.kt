package org.tasks.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.tasks.models.Task
import org.tasks.ui.theme.TasksTheme

@Composable
fun TaskList(tab: Int) {
    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(false) }
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task("Estudiar", false),
                Task("Hacer ejercicio", false),
                Task("Pasear al perro", false),
                Task("Visitar a mis amigos", false),
            )
        )
    }

    val grouped = tasks.groupBy { it.completed }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    loading = true
                    delay(500)
                    tasks = tasks.shuffled()
                    loading = false
                }
            },
        ) {
            if (loading) CircularProgressIndicator()
            else Text("Reordenar tareas")
        }

        Spacer(Modifier.height(16.dp))
        LazyColumn {
            val listToShow = if (tab == 0) grouped[false] else grouped[true]
            items(listToShow ?: emptyList()) { task ->
                TaskItem(task) {
                    tasks = tasks.map {
                        if (it.name == task.name) it.copy(completed = true)
                        else it
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TaskListPreview() {
    TasksTheme(darkTheme = true) {
        TaskList(0)
    }
}
