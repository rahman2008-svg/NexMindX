package com.nexvora.nexmindx.feature.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.ai.AIManager
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {

    val ai = remember { AIManager() }
    val scope = rememberCoroutineScope()

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("Ask something...") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("NexMind X 🤖", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        Text(output)

        Spacer(Modifier.height(12.dp))

        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Type here...") }
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            scope.launch {
                output = ai.generate(input)
            }
        }) {
            Text("Send")
        }
    }
}
