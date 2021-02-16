package com.app.infythree.data.api

import com.app.infythree.data.model.CountryMainModel
import com.app.infythree.data.model.CountryModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("facts.json")
    fun getApi():Observable<CountryMainModel>
}