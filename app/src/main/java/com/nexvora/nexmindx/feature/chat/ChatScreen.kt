package com.nexvora.nexmindx.feature.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.ai.AIManager
import com.nexvora.nexmindx.core.model.ModelManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {

    val context = LocalContext.current

    val ai = remember { AIManager() }
    val modelManager = remember { ModelManager(context) }

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("👋 Ask me anything...") }

    var modelStatus by remember { mutableStateOf("📦 Model Not Downloaded") }
    var downloadProgress by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🤖 NexMindX AI",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = modelStatus)

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = downloadProgress / 100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // MODEL DOWNLOAD BUTTON
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    modelManager.ensureModel { progress ->
                        downloadProgress = progress
                        modelStatus = "⬇ Downloading Model... $progress%"
                    }

                    modelStatus = "✅ Model Ready (Offline AI Active)"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download AI Model")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // OUTPUT BOX
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

        // INPUT FIELD
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type your message...") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // SEND BUTTON
        Button(
            onClick = {
                if (input.isNotBlank()) {

                    // ⚠️ Temporary AI (real LLM will come next step)
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
