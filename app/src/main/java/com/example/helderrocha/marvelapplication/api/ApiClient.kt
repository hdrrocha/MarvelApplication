package com.example.helderrocha.marvelapplication.api

import com.example.helderrocha.marvelapplication.model.Response
import io.reactivex.Observable
import javax.inject.Inject


class ApiClient @Inject constructor(private val marvelApi: MarvelApiApi) {
    fun characters(apiKey: String, hashApi: String, offset: Long): Observable<Response> {
        return marvelApi.characters(apiKey, hashApi, offset)
    }

    fun charactersId(id: Long, apiKey: String, hashApi: String): Observable<Response> {
        return marvelApi.charactersId(id, apiKey, hashApi)
    }

    fun charactersStory(id: Long, apiKey: String, hashApi: String): Observable<Response> {
        return marvelApi.charactersStory(id, apiKey, hashApi)
    }
}