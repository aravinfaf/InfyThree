package com.app.infythree.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.infythree.data.api.ApiService
import com.app.infythree.data.api.RetrofitBuilder
import com.app.infythree.data.model.CountryMainModel
import com.app.infythree.data.model.CountryModel
import com.app.infythree.utils.Resource
import com.app.infythree.utils.Resource.Companion.error
import com.app.infythree.utils.Resource.Companion.loading
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel : ViewModel() {

    var countrylist  = arrayListOf<CountryModel>()
    private val livedata = MutableLiveData<com.app.infythree.utils.Resource<List<CountryModel>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        livedata.postValue(loading(null))

        val apiService : ApiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)

         apiService.getApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response -> onResponse(response)},
                {error -> onError(error)
            })
    }

    private fun onError(error: Throwable) {
        livedata.postValue(error(error.message.toString()))
    }

    private fun onResponse(response: CountryMainModel) {
        countrylist = response.rows as ArrayList<CountryModel>
        livedata.postValue(com.app.infythree.utils.Resource.success(this.countrylist))
    }

    fun getData() : LiveData<com.app.infythree.utils.Resource<List<CountryModel>>>{
        return livedata
    }
}


