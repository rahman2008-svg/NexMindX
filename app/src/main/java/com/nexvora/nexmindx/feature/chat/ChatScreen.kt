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
    val ai = remember { AIManager(context) }
    val scope = rememberCoroutineScope()

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("👋 Ready") }
    var progress by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {

        Text("🤖 NexMindX AI", style = MaterialTheme.typography.titleLarge)

        if (loading) {
            Text("Downloading Model... $progress%")
            LinearProgressIndicator(progress = progress / 100f)
        }

        Spacer(Modifier.height(10.dp))

        Card(Modifier.fillMaxWidth().weight(1f)) {
            Text(output, Modifier.padding(12.dp))
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

                scope.launch {
                    loading = true

                    output = ai.ask(input) {
                        progress = it
                    }

                    loading = false
                    input = ""
                }
            }
        ) {
            Text("Send")
        }
    }
}
