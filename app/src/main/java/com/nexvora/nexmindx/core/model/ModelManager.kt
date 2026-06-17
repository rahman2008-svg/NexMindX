package com.nexvora.nexmindx.core.model

import android.content.Context

class ModelManager(private val context: Context) {

    private val downloader = ModelDownloader(context)

    fun getModelPath(): String {
        return downloader.getModelFile().absolutePath
    }

    suspend fun ensureModel(onProgress: (Int) -> Unit): String {
        return downloader.downloadModel(onProgress).absolutePath
    }
}
