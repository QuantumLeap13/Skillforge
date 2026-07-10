package com.example.skillforge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skillforge.data.model.ApiResponse
import com.example.skillforge.data.repository.SkillRepository
import kotlinx.coroutines.launch

class SkillViewModel : ViewModel() {

    private val repository = SkillRepository()

    val data = MutableLiveData<ApiResponse>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun fetchData() {
        viewModelScope.launch {
            try {
                loading.value = true
                data.value = repository.getData()
            } catch (e: Exception) {
                error.value = e.message ?: "Something went wrong"
            } finally {
                loading.value = false
            }
        }
    }
}