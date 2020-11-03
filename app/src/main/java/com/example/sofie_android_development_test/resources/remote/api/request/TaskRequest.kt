package com.example.sofie_android_development_test.resources.remote.api.request

import com.squareup.moshi.Json

data class TaskRequest(
    @Json(name = "email")
    val email: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String
)