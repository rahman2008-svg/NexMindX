package com.nexvora.nexmindx.core.model

import android.content.Context

class ModelManager(private val context: Context) {

    private val downloader = ModelDownloader(context)

    fun isReady(): Boolean {
        return downloader.file().exists()
    }

    suspend fun ensure(onProgress: (Int) -> Unit): String {
        return downloader.download(onProgress).absolutePath
    }
}
