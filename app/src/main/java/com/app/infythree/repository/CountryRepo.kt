package com.app.infytwo.repository

import com.app.infythree.data.api.ApiService
import com.app.infythree.data.api.RetrofitBuilder

class CountryRepo {
    private var apiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)
    fun getApiDetails() = apiService.getApi()
}