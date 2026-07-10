package com.example.skillforge.data.repository

import com.example.skillforge.data.model.ApiResponse
import com.example.skillforge.data.remote.RetrofitInstance

class SkillRepository {

    suspend fun getData(): ApiResponse {
        return RetrofitInstance.api.getData()
    }
}