package com.example.sofie_android_development_test.resources.repository

import com.example.sofie_android_development_test.domain.repository.ITaskRepository
import com.example.sofie_android_development_test.resources.remote.api.SofieAPI
import com.example.sofie_android_development_test.resources.remote.api.request.TaskRequest
import com.example.sofie_android_development_test.resources.remote.api.response.toModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class TaskRepository(private val api: SofieAPI) : ITaskRepository {
    override suspend fun list() = withContext(IO){
        api.list().await().data.map { it.toModel() }
    }

    override suspend fun create(request: TaskRequest) = withContext(IO){
        api.create(request).await().toModel()
    }

    override suspend fun update(request: TaskRequest, id: String) = withContext(IO){
        api.update(id, request).await().toModel()
    }
}