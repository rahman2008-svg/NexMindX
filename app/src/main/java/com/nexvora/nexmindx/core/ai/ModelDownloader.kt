package com.nexvora.nexmindx.core.ai

import java.io.File
import java.net.URL

object ModelDownloader {

    fun download() {
        val file = File(ModelConfig.MODEL_PATH)

        if (file.exists()) return

        URL(ModelConfig.MODEL_URL).openStream().use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
}
