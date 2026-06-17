package com.nexvora.nexmindx.core.ai

import android.content.Context
import com.nexvora.nexmindx.core.model.ModelManager

class AIManager(context: Context) {

    private val modelManager = ModelManager(context)

    suspend fun ask(prompt: String, onProgress: (Int) -> Unit = {}): String {

        val path = modelManager.ensureModel(onProgress)

        return "🤖 Model Ready:\n$path\n\nReply: $prompt"
    }
}
