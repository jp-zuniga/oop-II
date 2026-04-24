package org.tasks.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tasks.ui.components.TaskList
import org.tasks.ui.theme.TasksTheme

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tareas", "Completadas")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(40.dp))
        Text(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Gestor de Tareas",
        )

        Spacer(Modifier.height(40.dp))
        TabRow(selectedTab) {
            tabs.forEachIndexed { idx, title ->
                Tab(
                    onClick = { selectedTab = idx },
                    selected = selectedTab == idx,
                    text = { Text(title) },
                )
            }
        }

        TaskList(selectedTab)
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    TasksTheme(darkTheme = true) { MainScreen() }
}
