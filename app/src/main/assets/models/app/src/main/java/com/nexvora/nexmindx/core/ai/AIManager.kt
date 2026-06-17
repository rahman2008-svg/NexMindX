package com.nexvora.nexmindx.core.ai

class AIManager : AIEngine {

    override suspend fun generate(prompt: String): String {

        // TEMP AI RESPONSE (until llama.cpp connect)
        return """
        🤖 NexMind AI (TinyLlama Ready)

        You asked: $prompt

        Response: This is placeholder AI response.
        Next step: We will connect real TinyLlama model.
        """.trimIndent()
    }
}
