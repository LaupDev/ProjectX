package com.laupdev.projectx.domain.workers.firestore

import com.google.firebase.firestore.QuerySnapshot

interface IFirestoreWorker {

    suspend fun getHotelsUsingPaging(fromId: Long, limit: Long): QuerySnapshot?

    fun populateFirestore()
}