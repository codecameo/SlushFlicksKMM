package com.sifat.common.data.cache.manager

import com.google.firebase.firestore.FirebaseFirestore
import com.sifat.common.data.cache.manager.DataManagerConstants.COLLECTION_NAME
import com.sifat.common.data.cache.manager.DataManagerConstants.MOVIE_COLLECTION_ID
import com.sifat.common.data.cache.manager.DataManagerConstants.TV_COLLECTION_ID
import com.sifat.common.data.cache.model.CollectionEntity
import com.sifat.common.data.cache.model.CollectionFireStoreResponse
import com.sifat.common.data.remote.StatusCode.EMPTY_RESPONSE
import com.sifat.common.data.remote.StatusCode.INTERNAL_ERROR
import com.sifat.common.data.state.DataState
import com.sifat.common.data.state.DataState.Error
import com.sifat.common.data.state.DataState.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.resumeCancellableWith
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.Result.Companion.success

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class FireStoreManagerImpl(
    private val fireStore: FirebaseFirestore
) : FireStoreManager {

    override suspend fun getMovieCollections() = getCollection(MOVIE_COLLECTION_ID)

    override suspend fun getTvCollections() = getCollection(TV_COLLECTION_ID)

    private suspend fun getCollection(collectionId: String) =
        suspendCancellableCoroutine<DataState<List<CollectionEntity>>> { coroutine ->
            fireStore.collection(COLLECTION_NAME).document(collectionId)
                .get()
                .addOnSuccessListener { document ->
                    document?.data?.let {
                        try {
                            coroutine.resumeCancellableWith(
                                success(
                                    Success(
                                        data = document.toObject(
                                            CollectionFireStoreResponse::class.java
                                        )?.collections
                                    )
                                )
                            )
                        } catch (ex: Exception) {
                            coroutine.resumeCancellableWith(success(Error(statusCode = INTERNAL_ERROR)))
                        }
                    }
                        ?: coroutine.resumeCancellableWith(success(Error(statusCode = EMPTY_RESPONSE)))
                }
                .addOnFailureListener { exception ->
                    coroutine.resumeCancellableWith(success(Error(errorMessage = exception.message)))
                }
        }
}
