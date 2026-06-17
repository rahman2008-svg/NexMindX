package com.nexvora.nexmindx.core.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class ModelDownloader(private val context: Context) {

    private val url =
        "https://huggingface.co/TheBloke/TinyLlama-1.1B-Chat-v1.0-GGUF/resolve/main/tinyllama-1.1b-chat-v1.0.Q4_0.gguf"

    private val fileName = "tinyllama.gguf"

    fun file(): File = File(context.filesDir, fileName)

    suspend fun download(onProgress: (Int) -> Unit): File {
        return withContext(Dispatchers.IO) {

            val file = file()

            if (file.exists() && file.length() > 10_000_000) {
                return@withContext file
            }

            val conn = URL(url).openConnection()
            val size = conn.contentLength.coerceAtLeast(1)

            conn.getInputStream().use { input ->
                file.outputStream().use { output ->

                    val buffer = ByteArray(8 * 1024)
                    var read: Int
                    var total = 0

                    while (input.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                        total += read

                        onProgress((total * 100 / size))
                    }
                }
            }

            file
        }
    }
}
