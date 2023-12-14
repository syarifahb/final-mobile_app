package com.D121211074.makeup.data

import com.D121211074.makeup.data.repository.MakeupRepository
import com.D121211074.makeup.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppCointainer {
    val makeupRepository: MakeupRepository
}

class DefaultAppContainer: AppCointainer {

    private val BASE_URL = "http://makeup-api.herokuapp.com"

    val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val makeupRepository: MakeupRepository
        get() = MakeupRepository(retrofitService)

}