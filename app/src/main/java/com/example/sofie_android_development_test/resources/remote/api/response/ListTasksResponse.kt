package com.example.sofie_android_development_test.resources.remote.api.response

import com.squareup.moshi.Json

data class ListTasksResponse(
    @Json(name = "data") val data: List<TaskResponse>
)