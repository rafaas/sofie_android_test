package com.example.sofie_android_development_test.resources.remote.api

import com.example.sofie_android_development_test.resources.remote.api.request.TaskRequest
import com.example.sofie_android_development_test.resources.remote.api.response.CreateTaskDataResponse
import com.example.sofie_android_development_test.resources.remote.api.response.ListTasksResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface SofieAPI {
    @GET("beta/task")
    fun list(): Deferred<ListTasksResponse>

    @POST("beta/task")
    fun create(@Body taskRequest: TaskRequest): Deferred<CreateTaskDataResponse>

    @PUT("beta/task/{id}")
    fun update(@Path("id") id: String, @Body taskRequest: TaskRequest): Deferred<CreateTaskDataResponse>
}