package com.D121211074.makeup.data.repository

import com.D121211074.makeup.data.source.remote.ApiService
import com.D121211074.makeup.data.response.GetMakeupResponse

class MakeupRepository(private val apiService: ApiService) {

    suspend fun getMakeup(brand: String, product_type: String): GetMakeupResponse {
        return apiService.getMakeup(brand, product_type)
    }
}