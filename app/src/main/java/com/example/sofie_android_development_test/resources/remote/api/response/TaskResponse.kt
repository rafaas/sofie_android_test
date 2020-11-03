package com.example.sofie_android_development_test.resources.remote.api.response

import com.squareup.moshi.Json

data class TaskResponse(
    @Json(name = "title") val title: String,
    @Json(name = "email") val email: String,
    @Json(name = "description") val description: String,
    @Json(name = "_id") val _id: String
)