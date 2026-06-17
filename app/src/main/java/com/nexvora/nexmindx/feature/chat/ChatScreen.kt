package com.nexvora.nexmindx.feature.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.ai.AIManager

@Composable
fun ChatScreen() {

    val ai = remember { AIManager() }
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("Ask something...") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("NexMind X Chat 🚀", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        Text(output)

        Spacer(Modifier.height(12.dp))

        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Type prompt...") }
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            output = "Thinking..."
            output = ai.generateResponse(input).toString()
        }) {
            Text("Send")
        }
    }
}
