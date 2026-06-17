package com.nexvora.nexmindx.core.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class ModelDownloader(private val context: Context) {

    private val modelUrl =
        "https://huggingface.co/TheBloke/TinyLlama-1.1B-Chat-v1.0-GGUF/resolve/main/tinyllama-1.1b-chat-v1.0.Q4_0.gguf"

    private val fileName = "tinyllama.gguf"

    fun getModelFile(): File {
        return File(context.filesDir, fileName)
    }

    suspend fun downloadModel(onProgress: (Int) -> Unit): File {
        return withContext(Dispatchers.IO) {

            val file = getModelFile()

            if (file.exists() && file.length() > 0) {
                return@withContext file
            }

            val connection = URL(modelUrl).openConnection()
            connection.connectTimeout = 15000
            connection.readTimeout = 15000

            val size = connection.contentLength.coerceAtLeast(1)

            connection.getInputStream().use { input ->
                file.outputStream().use { output ->

                    val buffer = ByteArray(8 * 1024)
                    var bytesRead: Int
                    var downloaded = 0

                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                        downloaded += bytesRead

                        val progress = (downloaded * 100 / size)
                        onProgress(progress)
                    }
                }
            }

            file
        }
    }
}
