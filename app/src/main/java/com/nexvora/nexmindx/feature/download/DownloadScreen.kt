package com.nexvora.nexmindx.feature.download

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nexvora.nexmindx.core.model.ModelManager
import kotlinx.coroutines.launch

@Composable
fun DownloadScreen(onDone: () -> Unit) {

    val context = LocalContext.current
    val manager = remember { ModelManager(context) }
    val scope = rememberCoroutineScope()

    var progress by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("🤖 Download AI Model")

        Spacer(Modifier.height(10.dp))

        if (loading) {
            Text("Downloading... $progress%")
            LinearProgressIndicator(progress = progress / 100f)
        }

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    loading = true

                    manager.ensure {
                        progress = it
                    }

                    loading = false
                    onDone()
                }
            }
        ) {
            Text("Download Model")
        }
    }
}
