package com.example.task5

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatApiDataResponseBody(
    @Json(name = "id") val id: String?,
    @Json(name = "url") val imageUrl: String?
)
