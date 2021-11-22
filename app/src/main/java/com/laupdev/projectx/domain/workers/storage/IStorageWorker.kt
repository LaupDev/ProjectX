package com.laupdev.projectx.domain.workers.storage

interface IStorageWorker {
    suspend fun loadPictureToFileSystem()
}