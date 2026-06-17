package com.nexvora.nexmindx.feature.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.ai.AIManager
import com.nexvora.nexmindx.core.model.ModelManager
import kotlinx.coroutines.launch

@Composable
fun ChatScreen() {

    val context = LocalContext.current

    val ai = remember { AIManager() }
    val modelManager = remember { ModelManager(context) }

    val scope = rememberCoroutineScope()

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

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = modelStatus)

        Spacer(modifier = Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = (downloadProgress.coerceIn(0, 100)) / 100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 📥 DOWNLOAD BUTTON
        Button(
            onClick = {
                scope.launch {

                    modelStatus = "⬇ Starting download..."

                    val path = modelManager.ensureModel { progress ->
                        downloadProgress = progress
                        modelStatus = "⬇ Downloading... $progress%"
                    }

                    modelStatus = "✅ Model Ready"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download AI Model")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 💬 CHAT OUTPUT
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

        // ✍ INPUT
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Type message...") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🚀 SEND BUTTON
        Button(
            onClick = {
                if (input.isNotBlank()) {

                    // ⚠️ TEMP AI RESPONSE (REAL LLM NEXT STEP)
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
