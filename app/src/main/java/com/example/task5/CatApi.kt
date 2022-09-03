package com.example.task5

import com.example.task5.data.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("/v1/images/search")
    suspend fun getListOfCats(
        @Query("limit") limit: Int = 10,
        @Query("api_key") api_key: String = "c67e393c-f485-46f7-856d-599dd91cd59b"
    ): List<CatApiDataResponseBody>
}

object CatApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catService.getListOfCats()
                .map { Cat(it.id, it.imageUrl) }
        }
    }
}