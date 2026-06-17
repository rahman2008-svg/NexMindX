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
    var output by remember { mutableStateOf("👋 Ask me anything...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🤖 NexMindX AI",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Output box
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = output,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Input field
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type your message...") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Send button
        Button(
            onClick = {
                if (input.isNotBlank()) {
                    output = ai.ask(input)
                    input = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send")
        }
    }
}
