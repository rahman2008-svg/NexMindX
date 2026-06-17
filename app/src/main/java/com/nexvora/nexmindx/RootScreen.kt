package com.nexvora.nexmindx

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.nexvora.nexmindx.feature.chat.ChatScreen
import com.nexvora.nexmindx.feature.download.DownloadScreen
import com.nexvora.nexmindx.core.model.ModelManager

@Composable
fun RootScreen() {

    val context = LocalContext.current
    val manager = remember { ModelManager(context) }

    var ready by remember { mutableStateOf(manager.isReady()) }

    if (ready) {
        ChatScreen()
    } else {
        DownloadScreen {
            ready = true
        }
    }
}
