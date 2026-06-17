package com.nexvora.nexmindx.core.ai

import java.io.File

class LlamaEngine {

    fun generate(prompt: String): String {

        val modelExists = File(ModelConfig.MODEL_PATH).exists()

        return if (modelExists) {
            "🧠 TinyLlama model loaded (next step: llama.cpp native integration)"
        } else {
            "⚠️ Model not downloaded yet"
        }
    }
}
