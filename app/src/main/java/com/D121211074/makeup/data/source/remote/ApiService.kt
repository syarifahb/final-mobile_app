package com.D121211074.makeup.data.source.remote

import com.D121211074.makeup.data.response.GetMakeupResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/products.json")
    suspend fun getMakeup(
        @Query("brand") brand: String = "nyx",
        @Query("product_type") product_type: String = "blush",
    ): GetMakeupResponse
}