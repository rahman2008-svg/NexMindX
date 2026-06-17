package com.nexvora.nexmindx.core.ai

class AIManager : AIEngine {

    override suspend fun generateResponse(prompt: String): String {
        return "🤖 NexMind AI Response:\n\nYou asked: $prompt\n\n(Offline engine will be connected soon)"
    }
}
