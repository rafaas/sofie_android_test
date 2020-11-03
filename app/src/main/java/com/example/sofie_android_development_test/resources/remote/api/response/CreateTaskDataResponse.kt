package com.example.sofie_android_development_test.resources.remote.api.response

import com.example.sofie_android_development_test.domain.model.Task
import com.squareup.moshi.Json

data class CreateTaskDataResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "data") val data: TaskResponse
)

fun CreateTaskDataResponse.toModel() = Task(
    data._id,
    data.email,
    data.title,
    data.description
)