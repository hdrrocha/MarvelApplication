package com.example.helderrocha.marvelapplication.api

import com.example.helderrocha.marvelapplication.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface MarvelApiApi {
    companion object {
        val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
        const val URL = "https://gateway.marvel.com/v1/public/"
        const val API_KEY = "4271fa5b039c96fb82623d121abf1326"
        const val API_HASH = "dba4e3a0288d0a5fdddf64aa6bae3d80"
        const val LIMIT = "2"
        const val OFFSET = "0"
        //        val TS = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        const val TS = this.ts
        val TS = "1"

    }

    @GET("characters?limit=7&ts=1")
    fun characters(
            @Query("apikey") apiKey: String,
            @Query("hash") hashApi: String,
            @Query("offset") offset: Long
    ): Observable<Response>
//    fun characters(
//            @Query("offset") offset: Int? = 0,
//            @Query("api_key") apiKey: String,
//            @Query("language") language: String,
//            @Query("page") page: Long,
//            @Query("region") region: String
//
//    ): Observable<Response>
}