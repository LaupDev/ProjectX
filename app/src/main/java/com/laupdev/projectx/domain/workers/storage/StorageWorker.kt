package com.laupdev.projectx.domain.workers.storage

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class StorageWorker(private val application: Application) : IStorageWorker {

    override suspend fun savePictureToInternalStorage(pictureName: String, url: String): String {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            Picasso.get().load(url).get().let { picture ->
                application.applicationContext.openFileOutput(pictureName, Context.MODE_PRIVATE)
                    .use {
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    }
            }
        }

        return File(application.filesDir, pictureName).path
    }
}