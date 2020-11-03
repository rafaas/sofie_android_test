package com.example.sofie_android_development_test.domain.repository

import com.example.sofie_android_development_test.domain.model.Task
import com.example.sofie_android_development_test.resources.remote.api.request.TaskRequest

interface TaskRepository {

    suspend fun list(): List<Task>

    suspend fun create(request: TaskRequest): Task

    suspend fun update(request: TaskRequest, id: String): Task

    suspend fun delete(id: String): Task
}