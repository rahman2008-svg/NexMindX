package com.nexvora.nexmindx.core.ai

interface AIEngine {
    suspend fun generate(prompt: String): String
}
