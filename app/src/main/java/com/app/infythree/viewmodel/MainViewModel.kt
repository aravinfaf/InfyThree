package com.app.infythree.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.infythree.data.model.CountryMainModel
import com.app.infythree.data.model.CountryModel
import com.app.infythree.utils.Resource.Companion.loading
import com.app.infytwo.repository.CountryRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: CountryRepo) : ViewModel() {

    var countrylist  = arrayListOf<CountryModel>()
    var livedata = MutableLiveData<com.app.infythree.utils.Resource<List<CountryModel>>>()
    lateinit var disposabledata : Disposable
    init {
        fetchUsers()
    }

    private fun fetchUsers() {

        livedata.value=(loading(null))

       disposabledata= repository.getApiDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response -> onResponse(response)},
                {error -> onError(error)
            })
    }

    private fun onError(error: Throwable) {
        livedata.value=(error(error.message.toString()))
    }

    private fun onResponse(response: CountryMainModel) {
        countrylist = response.rows as ArrayList<CountryModel>
        livedata.value=(com.app.infythree.utils.Resource.success(this.countrylist))
    }

    override fun onCleared() {
        super.onCleared()
        disposabledata.dispose()
    }
}


