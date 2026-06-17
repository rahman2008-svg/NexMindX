package com.nexvora.nexmindx.core.ai

interface AIEngine {
    suspend fun generateResponse(prompt: String): String
}
