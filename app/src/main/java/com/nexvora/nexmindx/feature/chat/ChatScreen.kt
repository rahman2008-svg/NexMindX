package com.nexvora.nexmindx.feature.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.ai.AIManager
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {

    val context = LocalContext.current

    // ✅ FIX 1: context pass করা
    val ai = remember { AIManager(context) }

    val scope = rememberCoroutineScope()

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("👋 Ready") }
    var progress by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("🤖 NexMindX AI", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(10.dp))

        if (loading) {
            Text("Loading Model... $progress%")
            LinearProgressIndicator(progress = progress / 100f)
        }

        Spacer(Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(output, modifier = Modifier.padding(12.dp))
        }

        Spacer(Modifier.height(10.dp))

        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                if (input.isBlank()) return@Button

                // ✅ FIX 2: coroutine ব্যবহার করা
                scope.launch {

                    loading = true

                    val response = ai.ask(input) { p ->
                        progress = p
                    }

                    output = response
                    loading = false
                    input = ""
                }
            }
        ) {
            Text("Send")
        }
    }
}
