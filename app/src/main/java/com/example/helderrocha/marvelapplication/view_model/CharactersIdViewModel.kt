package com.example.helderrocha.marvelapplication.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.helderrocha.marvelapplication.SchedulerProvider
import com.example.helderrocha.marvelapplication.api.ApiClient
import com.example.helderrocha.marvelapplication.api.MarvelApiApi
import com.example.helderrocha.marvelapplication.model.SuperHero
import javax.inject.Inject

class CharactersIdViewModel @Inject constructor(val api: ApiClient, private val schedulers: SchedulerProvider) : ViewModel() {
    val _data = MutableLiveData<List<SuperHero>>()
    val data: LiveData<List<SuperHero>> = _data

    fun getHero(id: Long) {
        api.charactersId(id, MarvelApiApi.API_KEY, MarvelApiApi.API_HASH).subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe({
                    _data.value = it.data.results
                }, {
                    Log.i("ERROR", it.message)
                })
    }
}