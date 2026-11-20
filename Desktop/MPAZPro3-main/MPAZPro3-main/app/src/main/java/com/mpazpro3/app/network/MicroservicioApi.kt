package com.mpazpro3.app.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MicroservicioApi {
    @POST("posts")
    fun guardarPuntaje(@Body puntaje: PuntajeRequest): Call<PuntajeResponse>
}