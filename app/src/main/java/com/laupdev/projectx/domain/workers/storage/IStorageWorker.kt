package com.laupdev.projectx.domain.workers.storage

import android.app.Application

interface IStorageWorker {
    suspend fun savePictureToInternalStorage(pictureName: String, url: String): String
}