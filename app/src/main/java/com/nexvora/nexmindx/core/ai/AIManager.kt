package com.nexvora.nexmindx.core.ai

class AIManager {

    private val engine = LlamaEngine()

    fun ask(prompt: String): String {
        return engine.generate(prompt)
    }
}
