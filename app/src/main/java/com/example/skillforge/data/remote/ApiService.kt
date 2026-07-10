package com.example.skillforge.data.remote

import com.example.skillforge.data.model.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("android-assesment/notes/refs/heads/main/data.json")
    suspend fun getData(): ApiResponse
}